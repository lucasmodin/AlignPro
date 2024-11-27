package alignpro.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    private int taskID;
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int estimatedTime;
    private String taskDescription;
    private String skillRequirement;
    private int subProjectID;


    //************************* Constructors *******************************//
    public Task(){
    }

    public Task(String taskName, String startDate, String endDate, int estimatedTime,
             String taskDescription, String skillRequirement, int subProjectID){
        this.taskName = taskName;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.estimatedTime = estimatedTime;
        this.taskDescription = taskDescription;
        this.skillRequirement = skillRequirement;
        this.subProjectID = subProjectID;
    }

    public Task(String taskName, LocalDate startDate, LocalDate endDate, int estimatedTime,
                String taskDescription, String skillRequirement, int subProjectID){
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedTime = estimatedTime;
        this.taskDescription = taskDescription;
        this.skillRequirement = skillRequirement;
        this.subProjectID = subProjectID;
    }




    //************************* Setter and getters *******************************//
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public void setStartDateString(String startDate){
        this.startDate = LocalDate.parse(startDate);
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getEndDateString(){
        return endDate.toString();
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setEndDateString(String endDate){
        this.endDate = LocalDate.parse(endDate);
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getSkillRequirement() {
        return skillRequirement;
    }

    public void setSkillRequirement(String skillRequirement) {
        this.skillRequirement = skillRequirement;
    }

    public int getSubProjectID() {
        return subProjectID;
    }

    public void setSubProjectID(int subProjectID) {
        this.subProjectID = subProjectID;
    }
}
