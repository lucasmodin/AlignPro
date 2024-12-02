package alignpro.Model.DTOModel;

import java.time.LocalDate;

public class SubProjectDTO {

    //TODO this is the filter string not relevant for SubProject as it already knows projects.
    //TODO however for consistency
    private String filter;

    private String subProjectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int sumTime;
    private String subProjectDescription;

    //Empty konstruct for now
    public SubProjectDTO(){}


    //************************* Setter and getters *******************************//
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
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

    public int getSumTime() {
        return sumTime;
    }

    public void setSumTime(int sumTime) {
        this.sumTime = sumTime;
    }

    public String getSubProjectDescription() {
        return subProjectDescription;
    }

    public void setSubProjectDescription(String subProjectDescription) {
        this.subProjectDescription = subProjectDescription;
    }

}
