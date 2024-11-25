package alignpro.Model;

import java.time.LocalDate;

public class Project {

    private int projectID;
    private String projectName;
    private LocalDate startDate;
    private LocalDate deadLine;
    private int totalTime;
    private String projectDescription;


    // empty constructor
    public Project (){}

    //Basic constructor with two formats for dates
    public Project (String projectName, String startDate,String deadLine, String projectDescription){
        this.projectName = projectName;
        this.startDate = LocalDate.parse(startDate);
        this.deadLine = LocalDate.parse(deadLine);
        this.projectDescription = projectDescription;
    }

    public Project (String projectName, LocalDate startDate,LocalDate deadLine, String projectDescription){
        this.projectName = projectName;
        this.startDate = startDate;
        this.deadLine = deadLine;
        this.projectDescription = projectDescription;
    }

    //Constructor including projectID
    public Project (int projectID, String projectName, String startDate,String deadLine, String projectDescription){
        this.projectID = projectID;
        this.projectName = projectName;
        this.startDate = LocalDate.parse(startDate);
        this.deadLine = LocalDate.parse(deadLine);
        this.projectDescription = projectDescription;
    }

    public Project (int projectID, String projectName, LocalDate startDate, LocalDate deadLine, String projectDescription){
        this.projectID = projectID;
        this.projectName = projectName;
        this.startDate = startDate;
        this.deadLine = deadLine;
        this.projectDescription = projectDescription;
    }

    //Constructor including projectID, totalTime
    public Project (int projectID, String projectName, String startDate,String deadLine, String projectDescription, int totalTime){
        this.projectID = projectID;
        this.projectName = projectName;
        this.startDate = LocalDate.parse(startDate);
        this.deadLine = LocalDate.parse(deadLine);
        this.projectDescription = projectDescription;
        this.totalTime = totalTime;
    }

    public Project (int projectID, String projectName, LocalDate startDate, LocalDate deadLine, String projectDescription, int totalTime){
        this.projectID = projectID;
        this.projectName = projectName;
        this.startDate = startDate;
        this.deadLine = deadLine;
        this.projectDescription = projectDescription;
        this.totalTime = totalTime;
    }

    //************************* Setter and getters *******************************//

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getStartDateString(){
        return startDate.toString();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public String getDeadlineString(){
        return deadLine.toString();
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public void setDeadLine(String deadline){
        this.deadLine = LocalDate.parse(deadline);
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        projectDescription = projectDescription;
    }

}
