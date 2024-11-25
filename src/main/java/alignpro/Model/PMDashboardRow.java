package alignpro.Model;

public class PMDashboardRow {
    //project data
    private String projectName;
    private String projectStartDate;
    private String projectDeadline;
    private String projectDescription;
    private int projectTotalTime;


    //subproject data
    private String subProjectName;
    private String subProjectStartDate;
    private String subProjectEndDate;
    private String subProjectDescription;
    private int subProjectTotalTime;

    //task data
    private String taskName;
    private String taskStartDate;
    private String taskEndDate;
    private String taskDescription;
    private int taskTotalTime;

    //subtask data
    private String subTaskName;
    private String subTaskStartDate;
    private String subTaskEndDate;
    private String subTaskDescription;
    private int subTaskTotalTime;

    public PMDashboardRow() {
    }

    public PMDashboardRow(
            String projectName, String projectStartDate, String projectDeadline, String projectDescription, int projectTotalTime,
            String subProjectName, String subProjectStartDate, String subProjectEndDate, String subProjectDescription, int subProjectTotalTime,
            String taskName, String taskStartDate, String taskEndDate, String taskDescription, int taskTotalTime,
            String subTaskName, String subTaskStartDate, String subTaskEndDate, String subTaskDescription, int subTaskTotalTime) {
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectDeadline = projectDeadline;
        this.projectDescription = projectDescription;
        this.projectTotalTime = projectTotalTime;

        this.subProjectName = subProjectName;
        this.subProjectStartDate = subProjectStartDate;
        this.subProjectEndDate = subProjectEndDate;
        this.subProjectDescription = subProjectDescription;
        this.subProjectTotalTime = subProjectTotalTime;

        this.taskName = taskName;
        this.taskStartDate = taskStartDate;
        this.taskEndDate = taskEndDate;
        this.taskDescription = taskDescription;
        this.taskTotalTime = taskTotalTime;

        this.subTaskName = subTaskName;
        this.subTaskStartDate = subTaskStartDate;
        this.subTaskEndDate = subTaskEndDate;
        this.subTaskDescription = subTaskDescription;
        this.subTaskTotalTime = subTaskTotalTime;
    }

    // Getters and Setters
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectDeadline() {
        return projectDeadline;
    }

    public void setProjectDeadline(String projectDeadline) {
        this.projectDeadline = projectDeadline;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public int getProjectTotalTime() {
        return projectTotalTime;
    }

    public void setProjectTotalTime(int projectTotalTime) {
        this.projectTotalTime = projectTotalTime;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }

    public String getSubProjectStartDate() {
        return subProjectStartDate;
    }

    public void setSubProjectStartDate(String subProjectStartDate) {
        this.subProjectStartDate = subProjectStartDate;
    }

    public String getSubProjectEndDate() {
        return subProjectEndDate;
    }

    public void setSubProjectEndDate(String subProjectEndDate) {
        this.subProjectEndDate = subProjectEndDate;
    }

    public String getSubProjectDescription() {
        return subProjectDescription;
    }

    public void setSubProjectDescription(String subProjectDescription) {
        this.subProjectDescription = subProjectDescription;
    }

    public int getSubProjectTotalTime() {
        return subProjectTotalTime;
    }

    public void setSubProjectTotalTime(int subProjectTotalTime) {
        this.subProjectTotalTime = subProjectTotalTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(String taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public String getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(String taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getTaskTotalTime() {
        return taskTotalTime;
    }

    public void setTaskTotalTime(int taskTotalTime) {
        this.taskTotalTime = taskTotalTime;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public String getSubTaskStartDate() {
        return subTaskStartDate;
    }

    public void setSubTaskStartDate(String subTaskStartDate) {
        this.subTaskStartDate = subTaskStartDate;
    }

    public String getSubTaskEndDate() {
        return subTaskEndDate;
    }

    public void setSubTaskEndDate(String subTaskEndDate) {
        this.subTaskEndDate = subTaskEndDate;
    }

    public String getSubTaskDescription() {
        return subTaskDescription;
    }

    public void setSubTaskDescription(String subTaskDescription) {
        this.subTaskDescription = subTaskDescription;
    }

    public int getSubTaskTotalTime() {
        return subTaskTotalTime;
    }

    public void setSubTaskTotalTime(int subTaskTotalTime) {
        this.subTaskTotalTime = subTaskTotalTime;
    }
}
