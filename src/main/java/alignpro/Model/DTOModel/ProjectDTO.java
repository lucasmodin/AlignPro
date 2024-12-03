package alignpro.Model.DTOModel;

import java.time.LocalDate;

public class ProjectDTO {

    //TODO this is the filter string not relevant for Projects as we will filter on project name
    //TODO however for consistency
    private String filter;

    private String projectName;
    private LocalDate startDate;
    private LocalDate deadLine;
    private int totalTime;
    private String projectDescription;

    //Empty konstructor for nu.
    public ProjectDTO (){}



    //************************* Setter and getters *******************************//

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
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
        this.projectDescription = projectDescription;
    }


}
