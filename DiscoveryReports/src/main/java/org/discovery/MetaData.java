package org.discovery;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root[] root = om.readValue(myJsonString, Root[].class); */
@Data
@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaData{
    @JsonProperty("GRADLE_BRANCH_DOCKER_TAG")
    public String gradleBranchDockerTag;
    @JsonProperty("build.commitId")
    public String buildCommitId;
    @JsonProperty("GRADLE_PROJECT_DOCKER_TAG")
    public String gradleProjectDockerTag;
    @JsonProperty("host.container.name")
    public String hostContainerName;
    @JsonProperty("JOB_USER")
    public String jobUser;
    @JsonProperty("metrics.path")
    public String metricsPath;
    @JsonProperty("JOB_NAME")
    public String jobName;
    @JsonProperty("GRADLE_HAS_UNCOMMITTED_CHANGES")
    public String gradleHasUncommittedChanges;
    @JsonProperty("build.version")
    public String buildVersion;
    @JsonProperty("GRADLE_IS_DEVELOP_BRANCH")
    public String gradleIsDevelopBranch;
    @JsonProperty("info.path")
    public String infoPath;
    @JsonProperty("host.container.environment")
    public String hostContainerEnvironment;
    @JsonProperty("GRADLE_IS_TAGGED_VERSION")
    public String gradleIsTaggedVersion;
    @JsonProperty("container.name")
    public String containerName;
    @JsonProperty("GRADLE_PROJECT_VERSION")
    public String gradleProjectVersion;
    @JsonProperty("startup")
    public String startup;
    @JsonProperty("GRADLE_GIT_BRANCH")
    public String gradleGitBranch;
    @JsonProperty("host.container.ipAddress")
    public String hostContainerIpAddress;
    @JsonProperty("host.name")
    public String hostName;
    @JsonProperty("APPLICATION_GROUP_NAME")
    public String applicationGroupName;
    @JsonProperty("APPLICATION_NAME")
    public String applicationName;
    @JsonProperty("REPOSITORY_PATH")
    public String repositoryPath;
    @JsonProperty("health.path")
    public String healthPath;
    @JsonProperty("host.container.domain")
    public String hostContainerDomain;
    @JsonProperty("APPLICATION_ARTIFACT_NAME")
    public String applicationArtifactName;
    @JsonProperty("container.environment")
    public String containerEnvironment;
    @JsonProperty("host.applicationIpAddress")
    public String hostApplicationIpAddress;
    @JsonProperty("host.container.color")
    public String hostContainerColor;
    @JsonProperty("GRADLE_IS_RELEASE_BRANCH")
    public String gradleIsReleaseBranch;
    @JsonProperty("management.port")
    public String managementPort;
    @JsonProperty("host.container.host")
    public String hostContainerHost;
    @JsonProperty("host.maxmemory")
    public String hostMaxmemory;
    @JsonProperty("GRADLE_IS_HOTFIX_BRANCH")
    public String gradeIsHostfixBranch;
    @JsonProperty("container.color")
    public String containerColor;
    @JsonProperty("host.container.availabilityZone")
    public String hostContainerAvailabilityZone;
    @JsonProperty("build.tag")
    public String buildTag;
    @JsonProperty("management.context-path")
    public String managementContextPath;
    @JsonProperty("GRADLE_GIT_COMMIT")
    public String gradleGitCommit;
    @JsonProperty("host.managementIpAddress")
    public String hostManagementIpAddress;
    @JsonProperty("host.container.memlimit")
    public String hostContainerMemlimit;

    public String getGradleBranchDockerTag() {
        return gradleBranchDockerTag;
    }

    public void setGradleBranchDockerTag(String gradleBranchDockerTag) {
        this.gradleBranchDockerTag = gradleBranchDockerTag;
    }

    public String getBuildCommitId() {
        return buildCommitId;
    }

    public void setBuildCommitId(String buildCommitId) {
        this.buildCommitId = buildCommitId;
    }

    public String getGradleProjectDockerTag() {
        return gradleProjectDockerTag;
    }

    public void setGradleProjectDockerTag(String gradleProjectDockerTag) {
        this.gradleProjectDockerTag = gradleProjectDockerTag;
    }

    public String getHostContainerName() {
        return hostContainerName;
    }

    public void setHostContainerName(String hostContainerName) {
        this.hostContainerName = hostContainerName;
    }

    public String getJobUser() {
        return jobUser;
    }

    public void setJobUser(String jobUser) {
        this.jobUser = jobUser;
    }

    public String getMetricsPath() {
        return metricsPath;
    }

    public void setMetricsPath(String metricsPath) {
        this.metricsPath = metricsPath;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getGradleHasUncommittedChanges() {
        return gradleHasUncommittedChanges;
    }

    public void setGradleHasUncommittedChanges(String gradleHasUncommittedChanges) {
        this.gradleHasUncommittedChanges = gradleHasUncommittedChanges;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getGradleIsDevelopBranch() {
        return gradleIsDevelopBranch;
    }

    public void setGradleIsDevelopBranch(String gradleIsDevelopBranch) {
        this.gradleIsDevelopBranch = gradleIsDevelopBranch;
    }

    public String getInfoPath() {
        return infoPath;
    }

    public void setInfoPath(String infoPath) {
        this.infoPath = infoPath;
    }

    public String getHostContainerEnvironment() {
        return hostContainerEnvironment;
    }

    public void setHostContainerEnvironment(String hostContainerEnvironment) {
        this.hostContainerEnvironment = hostContainerEnvironment;
    }

    public String getGradleIsTaggedVersion() {
        return gradleIsTaggedVersion;
    }

    public void setGradleIsTaggedVersion(String gradleIsTaggedVersion) {
        this.gradleIsTaggedVersion = gradleIsTaggedVersion;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getGradleProjectVersion() {
        return gradleProjectVersion;
    }

    public void setGradleProjectVersion(String gradleProjectVersion) {
        this.gradleProjectVersion = gradleProjectVersion;
    }

    public String getStartup() {
        return startup;
    }

    public void setStartup(String startup) {
        this.startup = startup;
    }

    public String getGradleGitBranch() {
        return gradleGitBranch;
    }

    public void setGradleGitBranch(String gradleGitBranch) {
        this.gradleGitBranch = gradleGitBranch;
    }

    public String getHostContainerIpAddress() {
        return hostContainerIpAddress;
    }

    public void setHostContainerIpAddress(String hostContainerIpAddress) {
        this.hostContainerIpAddress = hostContainerIpAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getApplicationGroupName() {
        return applicationGroupName;
    }

    public void setApplicationGroupName(String applicationGroupName) {
        this.applicationGroupName = applicationGroupName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getRepositoryPath() {
        return repositoryPath;
    }

    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public String getHealthPath() {
        return healthPath;
    }

    public void setHealthPath(String healthPath) {
        this.healthPath = healthPath;
    }

    public String getHostContainerDomain() {
        return hostContainerDomain;
    }

    public void setHostContainerDomain(String hostContainerDomain) {
        this.hostContainerDomain = hostContainerDomain;
    }

    public String getApplicationArtifactName() {
        return applicationArtifactName;
    }

    public void setApplicationArtifactName(String applicationArtifactName) {
        this.applicationArtifactName = applicationArtifactName;
    }

    public String getContainerEnvironment() {
        return containerEnvironment;
    }

    public void setContainerEnvironment(String containerEnvironment) {
        this.containerEnvironment = containerEnvironment;
    }

    public String getHostApplicationIpAddress() {
        return hostApplicationIpAddress;
    }

    public void setHostApplicationIpAddress(String hostApplicationIpAddress) {
        this.hostApplicationIpAddress = hostApplicationIpAddress;
    }

    public String getHostContainerColor() {
        return hostContainerColor;
    }

    public void setHostContainerColor(String hostContainerColor) {
        this.hostContainerColor = hostContainerColor;
    }

    public String getGradleIsReleaseBranch() {
        return gradleIsReleaseBranch;
    }

    public void setGradleIsReleaseBranch(String gradleIsReleaseBranch) {
        this.gradleIsReleaseBranch = gradleIsReleaseBranch;
    }

    public String getManagementPort() {
        return managementPort;
    }

    public void setManagementPort(String managementPort) {
        this.managementPort = managementPort;
    }

    public String getHostContainerHost() {
        return hostContainerHost;
    }

    public void setHostContainerHost(String hostContainerHost) {
        this.hostContainerHost = hostContainerHost;
    }

    public String getHostMaxmemory() {
        return hostMaxmemory;
    }

    public void setHostMaxmemory(String hostMaxmemory) {
        this.hostMaxmemory = hostMaxmemory;
    }

    public String getGradeIsHostfixBranch() {
        return gradeIsHostfixBranch;
    }

    public void setGradeIsHostfixBranch(String gradeIsHostfixBranch) {
        this.gradeIsHostfixBranch = gradeIsHostfixBranch;
    }

    public String getContainerColor() {
        return containerColor;
    }

    public void setContainerColor(String containerColor) {
        this.containerColor = containerColor;
    }

    public String getHostContainerAvailabilityZone() {
        return hostContainerAvailabilityZone;
    }

    public void setHostContainerAvailabilityZone(String hostContainerAvailabilityZone) {
        this.hostContainerAvailabilityZone = hostContainerAvailabilityZone;
    }

    public String getBuildTag() {
        return buildTag;
    }

    public void setBuildTag(String buildTag) {
        this.buildTag = buildTag;
    }

    public String getManagementContextPath() {
        return managementContextPath;
    }

    public void setManagementContextPath(String managementContextPath) {
        this.managementContextPath = managementContextPath;
    }

    public String getGradleGitCommit() {
        return gradleGitCommit;
    }

    public void setGradleGitCommit(String gradleGitCommit) {
        this.gradleGitCommit = gradleGitCommit;
    }

    public String getHostManagementIpAddress() {
        return hostManagementIpAddress;
    }

    public void setHostManagementIpAddress(String hostManagementIpAddress) {
        this.hostManagementIpAddress = hostManagementIpAddress;
    }

    public String getHostContainerMemlimit() {
        return hostContainerMemlimit;
    }

    public void setHostContainerMemlimit(String hostContainerMemlimit) {
        this.hostContainerMemlimit = hostContainerMemlimit;
    }

    public String getHostEnvironment() {
        return hostEnvironment;
    }

    public void setHostEnvironment(String hostEnvironment) {
        this.hostEnvironment = hostEnvironment;
    }

    @JsonProperty("host.environment")
    public String hostEnvironment;
}



