package alignpro.Model.DTOModel;

import java.time.LocalDate;

public class SubTaskDTO {

    //TODO this is the filter string it needs to learn its project.
    //TODO write logic there makes SubTask knows it project
    private String filter;
    private int subTaskID;
    private String subTaskName;
    private String subTaskDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private int time;
    private int taskID;


    //Empty konstrucot
    public SubTaskDTO(){}



    //************************* Setter and getters *******************************//
    public int getSubTaskID() {
        return subTaskID;
    }

    public void setSubTaskID(int subTaskID) {
        this.subTaskID = subTaskID;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public String getSubTaskDescription() {
        return subTaskDescription;
    }

    public void setSubTaskDescription(String subTaskDescription) {
        this.subTaskDescription = subTaskDescription;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
