package alignpro.DTO;

import alignpro.Model.Projects.SubTask;
import alignpro.Model.Projects.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    private String taskName;
    private String startDate;
    private String endDate;
    private List<SubTaskDTO> subtasks;

    public String getTaskName() {
        return taskName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<SubTaskDTO> getSubtasks() {
        return subtasks;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setSubtasks(List<SubTaskDTO> subtasks) {
        this.subtasks = subtasks;
    }
}
