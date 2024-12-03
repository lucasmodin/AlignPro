package alignpro.Model.DTOModel;

import java.time.LocalDate;

public class TaskDTO {

    //TODO this is the filter string it needs to learn its project.
    //TODO write logic there makes SubTask knows it project
    private String filter;

    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int estimatedTime;
    private String taskDescription;


    //Empty konstructor for now
    public TaskDTO(){}


    //************************* Setter and getters *******************************//
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

}
