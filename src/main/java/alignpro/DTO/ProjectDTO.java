package alignpro.DTO;

import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubProject;

import java.util.ArrayList;
import java.util.List;

public class ProjectDTO {
    private String projectName;
    private String startDate;
    private String deadline;
    private int totalSumTime;
    private List<SubProjectDTO> subProjects;

    public String getProjectName() {
        return projectName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getTotalSumTime() {
        return totalSumTime;
    }

    public List<SubProjectDTO> getSubProjects() {
        return subProjects;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setTotalSumTime(int totalSumTime) {
        this.totalSumTime = totalSumTime;
    }

    public void setSubProjects(List<SubProjectDTO> subProjects) {
        this.subProjects = subProjects;
    }
}
