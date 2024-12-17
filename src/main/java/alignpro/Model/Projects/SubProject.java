package alignpro.Model.Projects;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class SubProject {
    private int subProjectID;
    private String subProjectName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private int sumTime;
    private String subProjectDescription;
    private int fkProjectID;

    //************************* Constructors *******************************//
    //Empty Constructor for easy init
    public SubProject(){
    }

    //Without sumtime
    public SubProject(String subProjectDescription, LocalDate startDate,
                      LocalDate endDate, String subProjectName) {
        this.subProjectDescription = subProjectDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subProjectName = subProjectName;
    }
    //String version of dates
    public SubProject(String subProjectDescription, String startDate,
                      String endDate, String subProjectName) {
        this.subProjectDescription = subProjectDescription;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.subProjectName = subProjectName;
    }

    //Constructor without pm/fk keys
    public SubProject(String subProjectDescription, int sumTime, LocalDate startDate,
                      LocalDate endDate, String subProjectName) {
        this.subProjectDescription = subProjectDescription;
        this.sumTime = sumTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subProjectName = subProjectName;
    }

    //String version of dates
    public SubProject(String subProjectDescription, int sumTime, String startDate,
                      String endDate, String subProjectName) {
        this.subProjectDescription = subProjectDescription;
        this.sumTime = sumTime;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.subProjectName = subProjectName;
    }

    //With foreign key
    public SubProject(String subProjectDescription, int sumTime, LocalDate startDate,
                      LocalDate endDate, String subProjectName, int subProjectID) {
        this.subProjectDescription = subProjectDescription;
        this.sumTime = sumTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subProjectName = subProjectName;
        this.subProjectID = subProjectID;
    }

    //String version of dates
    public SubProject(String subProjectDescription, int sumTime, String startDate,
                      String endDate, String subProjectName, int subProjectID) {
        this.subProjectDescription = subProjectDescription;
        this.sumTime = sumTime;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.subProjectName = subProjectName;
        this.subProjectID = subProjectID;
    }

    //With pk/fk keys
    public SubProject(int fkProjectID, String subProjectDescription, int sumTime, String startDate,
                      String endDate, String subProjectName, int subProjectID) {
        this.fkProjectID = fkProjectID;
        this.subProjectDescription = subProjectDescription;
        this.sumTime = sumTime;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.subProjectName = subProjectName;
        this.subProjectID = subProjectID;
    }

    //String version of dates
    public SubProject(int fkProjectID, String subProjectDescription, int sumTime, LocalDate startDate,
                      LocalDate endDate, String subProjectName, int subProjectID) {
        this.fkProjectID = fkProjectID;
        this.subProjectDescription = subProjectDescription;
        this.sumTime = sumTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subProjectName = subProjectName;
        this.subProjectID = subProjectID;
    }

    public SubProject(int subProjectID, String subProjectName, String startDate, String endDate,
                      String subProjectDescription, int fkProjectID) {
        this.subProjectID = subProjectID;
        this.subProjectName = subProjectName;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.subProjectDescription = subProjectDescription;
        this.fkProjectID = fkProjectID;
    }

    public SubProject(int subProjectID, String subProjectName, String startDate,
                      String endDate, String subProjectDescription) {
        this.subProjectID = subProjectID;
        this.subProjectName = subProjectName;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.subProjectDescription = subProjectDescription;
    }

    //************************* Setter and getters *******************************//
    public int getSubProjectID() {
        return subProjectID;
    }

    public void setSubProjectID(int subProjectID) {
        this.subProjectID = subProjectID;
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

    public String getStartDateString() {
        return startDate.toString();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getEndDateString() {
        return endDate.toString();
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
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

    public int getFkProjectID() {
        return fkProjectID;
    }

    public void setFkProjectID(int fkProjectID) {
        this.fkProjectID = fkProjectID;
    }
}
