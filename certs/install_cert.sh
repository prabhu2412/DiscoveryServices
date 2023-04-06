######### Certificate installation for Java 8 ##############

sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPRoot.crt" -alias JCPRoot
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/WebGateway.crt" -alias WebGateway
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/WebGateway-2.crt" -alias WebGateway2
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPMedAssurance.crt" -alias JCPMedAssurance
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPPolicyCA1.crt" -alias JCPPolicyCA1
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/registry.dp-dev.cert" -alias RegistryDpDev
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/maven.infra.dp-dev.jcpcloud2.net.cert" -alias MavenInfraDpDev
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/DEVISSUING01.crt" -alias DEVISSUING01
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/devroot.crt" -alias devroot
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPenneyRCA.crt" -alias JCPenneyRCA
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPenneyMCA3.crt" -alias JCPenneyMCA3
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPenneyMCA4.crt" -alias JCPenneyMCA4
sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPenneyMCA1.crt" -alias JCPenneyMCA1

######### Certificate installation for Java 11 ##############

#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPRoot.crt" -alias JCPRoot
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/WebGateway.crt" -alias WebGateway
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/WebGateway-2.crt" -alias WebGateway2
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPMedAssurance.crt" -alias JCPMedAssurance
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPPolicyCA1.crt" -alias JCPPolicyCA1
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/registry.dp-dev.cert" -alias RegistryDpDev
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/maven.infra.dp-dev.jcpcloud2.net.cert" -alias MavenInfraDpDev
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/DEVISSUING01.crt" -alias DEVISSUING01
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/devroot.crt" -alias devroot
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPenneyRCA.crt" -alias JCPenneyRCA
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPenneyMCA3.crt" -alias JCPenneyMCA3
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPenneyMCA4.crt" -alias JCPenneyMCA4
#sudo keytool -importcert -keystore "/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home/lib/security/cacerts" -storepass changeit -file "/Users/ps3/Downloads/certs/JCPenneyMCA1.crt" -alias JCPenneyMCA1
