package alignpro.DTO;

import alignpro.Model.Projects.SubProject;
import alignpro.Model.Projects.Task;

import java.util.ArrayList;
import java.util.List;

public class SubProjectDTO {
    private String subProjectName;
    private String startDate;
    private String endDate;
    private List<TaskDTO> tasks;

    public String getSubProjectName() {
        return subProjectName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
