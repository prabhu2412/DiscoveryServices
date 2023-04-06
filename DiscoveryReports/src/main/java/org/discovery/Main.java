package org.discovery;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Set<Object> services;
    private static Set<Object> environments;

    private static Properties environmentProperties;
    private static Properties configProperties;

    static {
        FileReader reader= null;
        try {
            reader = new FileReader("environments.properties");
            environmentProperties =new Properties();
            environmentProperties.load(reader);
            environments = environmentProperties.values().stream().collect(Collectors.toSet());
            configProperties=new Properties();
            reader = new FileReader("config.properties");
            configProperties.load(reader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        services = new HashSet<>();
        Map<String, List<Root>> discoveryServiceInfoEnvironment1=null;
        Map<String, List<Root>> discoveryServiceInfoEnvironment2=null;

        if(configProperties.get("gettags").toString().equalsIgnoreCase("true")){
            getTagsCall();
        }

        if (StringUtils.isNoneEmpty(configProperties.get("environment1").toString()))
            {

            try {
                getJsonElement(getServices(configProperties.get("environment1").toString()))
                        .getAsJsonArray().asList().forEach(
                        s -> services.add(s.getAsString().toLowerCase())
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (StringUtils.isNotEmpty(configProperties.get("environment1").toString())) {
                discoveryServiceInfoEnvironment1 =
                        getDiscoveryServiceInfo(configProperties.get("environment1").toString());
            }
            if (StringUtils.isNotEmpty(configProperties.get("environment2").toString())) {
                discoveryServiceInfoEnvironment2 =
                        getDiscoveryServiceInfo(configProperties.get("environment2").toString());
            }
        }

        if (configProperties.get("comparetagsreportenabled").toString()
                .equalsIgnoreCase("true")) {
            compareTags
                    (discoveryServiceInfoEnvironment1,
                            discoveryServiceInfoEnvironment2);
        }

        if (configProperties.get("comparecontainerreportenabled").toString()
                .equalsIgnoreCase("true")){
            compareContainersOfAllServices
                    (discoveryServiceInfoEnvironment1,
                            discoveryServiceInfoEnvironment2);
        }

        if (configProperties.get("hostcontainercomparisonenabled").toString()
                .equalsIgnoreCase("true")){
            getHostContainerCountOfAllServices
                    (discoveryServiceInfoEnvironment1,
                            discoveryServiceInfoEnvironment2);
        }
        System.out.println("*************Please check Tags.html file for final result*************");
    }

    private static void getHostContainerCountOfAllServices(Map<String, List<Root>> discoveryServiceInfoEnvironment1,
                                                           Map<String, List<Root>> discoveryServiceInfoEnvironment2) {
        String environment1 = configProperties.getProperty("environment1");
        String environment2 = configProperties.getProperty("environment2");
        System.out.println("Comparing containers...");
        try {
            getJsonElement(getServices(environment1)).getAsJsonArray().asList().forEach(
                    s -> services.add(s.getAsString().toLowerCase())
            );

            Map<String,List<String>> hostWiseContainerDetails1
                    = new HashMap<>();
            Map<String,List<String>> hostWiseContainerDetails2
                    = new HashMap<>();
            if (null!=discoveryServiceInfoEnvironment1) {
                hostWiseContainerDetails1 =
                        getHostWiseContainer(discoveryServiceInfoEnvironment1);
            }
            if (null!=discoveryServiceInfoEnvironment2) {
                hostWiseContainerDetails2 =
                        getHostWiseContainer(discoveryServiceInfoEnvironment2);
            }

            StringBuilder html = new StringBuilder();

            getStartHtmlElements(html, environment1, environment2, 1, 0);
            getHostWiseContainerHtml(html,
                    hostWiseContainerDetails1,
                    hostWiseContainerDetails2);
            getEndHtml(html);
            writeHtmlToFile(Constants.CONTAINER_COMP_FILE_PATH,
                    html,
            getFileNameToWriteHtml(environment1,
                    Constants.AND,
                    environment2,
                    Constants.HOST_CONTAINER_COMPARISON_FILE_NAME,
                    Constants.FILE_FORMAT));
            html=null;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void getHostWiseContainerHtml(StringBuilder html,
                                                 Map<String, List<String>> hostWiseContainerDetails1,
                                                 Map<String, List<String>> hostWiseContainerDetails2) {
        html.append("<tr>");
        html.append("<td>");
        writeColumn(html, hostWiseContainerDetails1);
        html.append("</td>");
        html.append("<td>");
        writeColumn(html, hostWiseContainerDetails2);
        html.append("</td>");
        html.append("</tr>");
    }


    private static void writeColumn(StringBuilder html,
                                    Map<String, List<String>> hostWiseContainerDetails1) {
        html.append("<table style='width:100%'>");
        html.append("<tr>");
        html.append("<th>").append("Host Name").append("</th>");
        html.append("<th>").append("Container Count").append("</th>");
        html.append("</tr>");
        hostWiseContainerDetails1.forEach(
                (hostName, containerNames) -> {
                    html.append("<tr><td style='text-align: left;font-weight: bold'>").append(hostName).append("</th>");
                    html.append("<td>").append(containerNames.size()).append("</td></tr>");
                    containerNames.forEach(
                            containerName ->{
                                html.append("<tr><td style='text-align: right'>").append(containerName).append("</td><td></td></tr>");
                            }
                    );

                }
        );
        html.append("</table>");
        html.append("</td>");

    }

    private static Map<String,List<String>> getHostWiseContainer(Map<String, List<Root>> discoveryServiceInfoEnvironment) {

        Map<String, List<String>> hostWiseContainerDetail
                = new HashMap<>();
        discoveryServiceInfoEnvironment.forEach(
                (key, value) -> {
                    value.stream().forEachOrdered(
                            root -> {
                                if (hostWiseContainerDetail.containsKey(root.getMetaData().getHostName())) {
                                    hostWiseContainerDetail.get(root.getMetaData().getHostName())
                                            .add(root.getMetaData().getHostContainerName());
                                } else {
                                    List<String> containers
                                            = new ArrayList<>();
                                    containers.add(root.getMetaData().getHostContainerName());
                                    hostWiseContainerDetail.put(root.getMetaData().getHostName(), containers);
                                }
                            }
                    );
                }
        );
        return hostWiseContainerDetail;
    }

    private static void compareContainersOfAllServices(Map<String, List<Root>> discoveryServiceInfoEnvironment1,
                                                       Map<String, List<Root>> discoveryServiceInfoEnvironment2) {
        String environment1 = configProperties.getProperty("environment1");
        String environment2 = configProperties.getProperty("environment2");
        System.out.println("Comparing containers...");
        try {
            getJsonElement(getServices(environment1)).getAsJsonArray().asList().forEach(
                    s -> services.add(s.getAsString().toLowerCase())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder html = new StringBuilder();

        getStartHtmlElements(html, environment1, environment2, 0, 1);
        getComparisonHtmlData(html,
                environment1,
                environment2,
                discoveryServiceInfoEnvironment1,
                discoveryServiceInfoEnvironment2, 3);
        getEndHtml(html);
        writeHtmlToFile(Constants.CONTAINER_COMP_FILE_PATH,
                html,
                getFileNameToWriteHtml(environment1,
                Constants.AND,
                environment2,
                Constants.CONTAINER_COMPARISON_FILE_NAME,
                Constants.FILE_FORMAT));
        html=null;
    }

    private static void compareTags(Map<String, List<Root>> discoveryServiceInfoEnvironment1,
                                    Map<String, List<Root>> discoveryServiceInfoEnvironment2) {

        String environment1 = configProperties.getProperty("environment1");
        String environment2 = configProperties.getProperty("environment2");
        System.out.println("Comparing tags...");
        try {
            getJsonElement(getServices(environment1)).getAsJsonArray().asList().forEach(
                    s -> services.add(s.getAsString().toLowerCase())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder html = new StringBuilder();
        getStartHtmlElements(html, environment1, environment2, 0, 1);
        getComparisonHtmlData(html,
                environment1,
                environment2,
                discoveryServiceInfoEnvironment1,
                discoveryServiceInfoEnvironment2, 2);
        getEndHtml(html);
        writeHtmlToFile(Constants.TAGS_COMP_FILE_PATH,
                html,
                getFileNameToWriteHtml(environment1,
                Constants.AND,
                environment2,
                Constants.TAG_COMPARISON_FILE_NAME,
                Constants.FILE_FORMAT));
        html=null;
    }

    private static String getFileNameToWriteHtml(String environment1,
                                                 String and,
                                                 String environment2,
                                                 String tagComparisonFileName,
                                                 String fileFormat) {
        StringBuilder fileName = new StringBuilder();
        return fileName.append(tagComparisonFileName)
                .append(environment1).append("_and_").append(environment2)
                .append("_")
                .append(getDateForFileCreation().replace(" ","_"))
                .append(Constants.FILE_FORMAT).toString();
    }

    private static String getDateForFileCreation() {
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        return simpleDateFormat.format(date).toString();
    }

    private static void getEndHtml(StringBuilder html) {
        html.append("</table></div>");
        html.append("</body></html>");
    }

    private static void getComparisonHtmlData(StringBuilder html,
                                              String environment1,
                                              String environment2,
                                              Map<String, List<Root>> discoveryServiceInfoEnvironment1,
                                              Map<String, List<Root>> discoveryServiceInfoEnvironment2,
                                              int comparisonFlag) {

        services.stream().forEach((service) ->
        {
            String comparisonValue1 = "", comparisonValue2 = "";
            comparisonValue1 = getEnvComparisonValue(discoveryServiceInfoEnvironment1,
                                                                service.toString(), comparisonFlag);
            comparisonValue2 = getEnvComparisonValue(discoveryServiceInfoEnvironment2,
                    service.toString(), comparisonFlag);

            html.append("<tr>");
            html.append("<td>").append(service.toString().toLowerCase()).append("</td>");

            if((discoveryServiceInfoEnvironment1.containsKey(service.toString().toLowerCase())
                    && discoveryServiceInfoEnvironment2.containsKey(service.toString().toLowerCase()))
                    && ((comparisonValue1.equalsIgnoreCase(comparisonValue2)))
            ){
                html.append("<td>").append(comparisonValue1).append("</td>");
                html.append("<td>").append(comparisonValue2).append("</td>");
                html.append("</tr>");
            } else if ((discoveryServiceInfoEnvironment1.containsKey(service.toString().toLowerCase())
                    && discoveryServiceInfoEnvironment2.containsKey(service.toString().toLowerCase()))
                    && !((comparisonValue1.equalsIgnoreCase(comparisonValue2)))
            ){
                html.append("<td>").append(comparisonValue1).append("</td>");
                html.append("<td style='background-color:#ff9933'>").append(comparisonValue2).append("</td>");
                html.append("</tr>");
            } else if((!discoveryServiceInfoEnvironment1.containsKey(service.toString().toLowerCase())
                    && discoveryServiceInfoEnvironment2.containsKey(service.toString().toLowerCase()))){
                html.append("<td style='background-color:#ffff66'>").append("").append("</td>");
                html.append("<td>").append(comparisonValue2).append("</td>");
                html.append("</tr>");
            }else if((discoveryServiceInfoEnvironment1.containsKey(service.toString().toLowerCase())
                    && !discoveryServiceInfoEnvironment2.containsKey(service.toString().toLowerCase()))){
                html.append("<td>").append(comparisonValue1).append("</td>");
                html.append("<td style='background-color:#ffff66'>").append("").append("</td>");
                html.append("</tr>");
            }else {
                html.append("<td style='background-color:#ffff66'>").append("").append("</td>");
                html.append("<td style='background-color:#ffff66'>").append("").append("</td>");
                html.append("</tr>");
            }
        });

    }

    private static String getEnvComparisonValue(Map<String, List<Root>> discoveryServiceInfoEnvironment,
                                                 String service, int comparisonFlag) {
        String value="";
        if (null != discoveryServiceInfoEnvironment &&
                null != discoveryServiceInfoEnvironment.get(service.toString().toLowerCase())) {
            if (comparisonFlag==1
                || comparisonFlag==2) {
                value = discoveryServiceInfoEnvironment.get(service.toString().toLowerCase()).get(0)
                        .getMetaData().getBuildTag();
            } else if (comparisonFlag==3) {
                value = discoveryServiceInfoEnvironment.get(service.toString().toLowerCase()).size()+"";
            }
        }

        return value;
    }

    private static void getStartHtmlElements(StringBuilder html,
                                                      String environment1,
                                                      String environment2,
                                             int containerFlag, int serviceFlag) {
        html.append("<!DOCTYPE html>");
        html.append("<html><head></head><style>");
        html.append("td, th {border: 1px solid #777; padding: 0.5rem; text-align: center; } table {border-collapse: collapse; } tbody tr:nth-child(odd) {background: #eee; } caption {font-size: 0.8rem; }");
        html.append("</style><body><div>");
        html.append("<table>");
        html.append("<tr>");
        html.append("<td style='background-color:#ff0000'></td>");
        html.append("<td>Mismatched Tags</td>");
        html.append("<td style='background-color:#ffff66'></td>");
        html.append("<td>Missing/Tags Not Available</td>");
        html.append("</tr>");
        html.append("</table>");

        html.append("<table>");
        html.append("<tr>");
        if (serviceFlag==1){
            html.append("<th style='text-align: center'>Service</th>");
        }
        if (StringUtils.isNotEmpty(environment1)) {
            html.append("<th style='text-align: center'>").append(environment1).append("</th>");
            /*if (containerFlag==1) {
                html.append("<th>").append("Container Count").append("</th>");
            }*/
        }
        if (StringUtils.isNotEmpty(environment2)) {
            html.append("<th style='text-align: center'>").append(environment2).append("</th>");
            /*if (containerFlag==1) {
                html.append("<th>").append("Container Count").append("</th>");
            }*/
        }
        html.append("</tr>");
    }

    private static void getTagsCall() {
        String environment = configProperties.getProperty("environment1");
        StringBuilder html = new StringBuilder();
        getStartHtmlElements(html, environment, "", 0, 1);
        if(checkEnvironmentPresent(environment)){
            System.out.println("Getting tags for environment:"+environment);
            getTags(environment, html);
        }else{
            System.out.println("Please try again...integration2a/integration3a/integration4a/loadtest1a/prod3/prod4/prod5\n");
            getTagsCall();
        }
        getEndHtml(html);
    }

    private static void getTags(String environment, StringBuilder html){

        try {
            getJsonElement(getServices(environment)).getAsJsonArray().asList().forEach(
                    s -> services.add(s.getAsString().toLowerCase())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, List<Root>> discoveryServiceInfoEnvironment1 = getDiscoveryServiceInfo(environment);

        services.stream().forEach(service ->{
            String comparisonValue1 = "", comparisonValue2 = "";
            comparisonValue1 = getEnvComparisonValue(discoveryServiceInfoEnvironment1,
                    service.toString(), 1);
            html.append("<tr>");
            if (discoveryServiceInfoEnvironment1.containsKey(service.toString().toLowerCase())) {
                html.append("<td>").append(service).append("</td>");
                html.append("<td>").append(comparisonValue1).append("</td>");
            }else{
                html.append("<td>").append(service).append("</td>");
                html.append("<td style='background-color:#ffff66'>").append("").append("</td>");
            }
            html.append("</tr>");
        });
        html.append("</table></div>");
        html.append("<div><table>");
        html.append("<td style='background-color:#ffff66'></td>");
        html.append("<td>Missing/Tags Not Available</td>");
        html.append("</table></div>");
        html.append("</body></html>");
        writeHtmlToFile(Constants.TAGS_FILE_PATH,
                html,
                getFileNameToWriteHtml(environment,
                        "",
                        "",
                        Constants.TAG_FILE_NAME,
                        Constants.FILE_FORMAT));
    }

    private static boolean checkEnvironmentPresent(String environment){
        return environments.stream().anyMatch(environment2 -> environment2.toString().equalsIgnoreCase(environment));
    }

    private static void writeHtmlToFile(String path,
                                        StringBuilder html,
                                        String fileName) {
        File file = new File(fileName);
        try {
            FileUtils.writeStringToFile(file,  html.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, List<Root>> getDiscoveryServiceInfo(String environment) {
        ObjectMapper objectMapper1 = new ObjectMapper();
        List<String> uris = getExtractedUris(environment);
        Map<String, List<Root>> servicesAndTags = new HashMap<>();
        uris.stream().forEach(s -> {
            try {
                JsonElement root = getJsonElement(s);
                if (null != root && root.getAsJsonArray().size()>0){
                    List<Root> rootObj = objectMapper1.readValue(
                            root.getAsJsonArray().toString(),
                            new TypeReference<List<Root>>(){});
                    if( null != rootObj) {
                        servicesAndTags.put(rootObj.get(0).getServiceId().toLowerCase()
                        , rootObj);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return servicesAndTags;
    }

    private static Map<String, String> getTagMap(String environment) {
        ObjectMapper objectMapper1 = new ObjectMapper();
        List<String> uris = getExtractedUris(environment);
        Map<String, String> servicesAndTags
                = new HashMap<>();
        uris.stream().forEach(s -> {
            try {
                JsonElement root = getJsonElement(s);

                if(!root.getAsJsonArray().isEmpty() && root.getAsJsonArray().size()>0) {
                    servicesAndTags.put(root.getAsJsonArray().get(0).getAsJsonObject().get("serviceId").getAsString().toLowerCase(),
                            root.getAsJsonArray().get(0).getAsJsonObject().get("metaData").getAsJsonObject().get("build.version").getAsString());
                }else{
                    servicesAndTags.put("","");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return servicesAndTags;
    }

    private static String getServices(String environment) {


        StringBuilder URI = new StringBuilder();
        URI.append(Constants.URI).append(Constants.DOT)
                .append(environment)
                .append(Constants.DOT);
        if(environment.contains("prod")) {
            URI.append(Constants.PROD_DOMAIN);
        }else {
            URI.append(Constants.LOWER_DOMAIN);
        }
        URI.append(Constants.DISCOVERY).toString();

        return URI.toString();
    }

    private static List<String> getExtractedUris(String environment) {
        List<String> URIs = new ArrayList<>();
        services.stream().forEach(
                services ->{
                    StringBuilder URI = new StringBuilder();
                    URI.append(Constants.URI).append(Constants.DOT)
                            .append(environment)
                            .append(Constants.DOT);
                    if(environment.contains("prod")) {
                        URI.append(Constants.PROD_DOMAIN);
                    }else {
                        URI.append(Constants.LOWER_DOMAIN);
                    }
                    URI.append(Constants.DISCOVERY).append(services.toString().toLowerCase()).toString();
                    URIs.add(URI.toString());
                });

        return URIs;
    }

    private static JsonElement getJsonElement(String URI) throws IOException {
        URLConnection request = new URL(URI).openConnection();
        request.connect();
        InputStreamReader inputStreamReader = new InputStreamReader((InputStream) request.getContent());
        // map to GSON objects
        JsonElement root = new JsonParser().parse(inputStreamReader);
        return root;
    }
}