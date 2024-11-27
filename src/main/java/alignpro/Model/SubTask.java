package alignpro.Model;

import java.time.LocalDate;

public class SubTask {
    private int subTaskID;
    private String subTaskName;
    private String subTaskDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private int time;
    private int taskID;
    private String skillRequirement;


    //************************* Constructors *******************************//
    public SubTask(){
    }

    //constructor with everything, and localdate object
    public SubTask(int subTaskID, String subTaskName, String subTaskDescription, LocalDate startDate,
                   LocalDate endDate, int time, int taskID, String skillRequirement) {
        this.subTaskID = subTaskID;
        this.subTaskName = subTaskName;
        this.subTaskDescription = subTaskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        this.taskID = taskID;
        this.skillRequirement = skillRequirement;
    }

    //constructor without pk(subtaskid) and with localdate
    public SubTask(String subTaskName, String subTaskDescription, LocalDate startDate, LocalDate endDate, int time, int taskID, String skillRequirement) {
        this.subTaskName = subTaskName;
        this.subTaskDescription = subTaskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
        this.taskID = taskID;
        this.skillRequirement = skillRequirement;
    }

    //constructor without pk(subtaskid) and with string date input
    public SubTask(String subTaskName, String subTaskDescription, String startDate, String endDate, int time, String skillRequirement, int taskID) {
        this.subTaskName = subTaskName;
        this.subTaskDescription = subTaskDescription;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.time = time;
        this.skillRequirement = skillRequirement;
        this.taskID = taskID;
    }

    //constructor without pk, skill and with string date input
    public SubTask(String subTaskName, String subTaskDescription, String startDate, String endDate, int time, int taskID) {
        this.subTaskName = subTaskName;
        this.subTaskDescription = subTaskDescription;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.time = time;
        this.taskID = taskID;
    }

    public SubTask(int subTaskID, String subTaskName, String subTaskDescription,
                   String startDate, String endDate, int time, String skillRequirement) {
        this.subTaskID = subTaskID;
        this.subTaskName = subTaskName;
        this.subTaskDescription = subTaskDescription;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.time = time;
        this.skillRequirement = skillRequirement;
    }


    //************************* Setter and getters *******************************//

    public int getSubTaskID() {
        return subTaskID;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public String getSubTaskDescription() {
        return subTaskDescription;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getStartDateString() {
        return startDate.toString();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getEndDateString() {
        return endDate.toString();
    }

    public int getTime() {
        return time;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getSkillRequirement() {
        return skillRequirement;
    }

    public void setSubTaskID(int subTaskID) {
        this.subTaskID = subTaskID;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public void setSubTaskDescription(String subTaskDescription) {
        this.subTaskDescription = subTaskDescription;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDateString) {
        this.startDate = LocalDate.parse(startDateString);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(String endDateString){
        this.endDate = LocalDate.parse(endDateString);
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setSkillRequirement(String skillRequirement) {
        this.skillRequirement = skillRequirement;
    }
}
