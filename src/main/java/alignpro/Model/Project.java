package alignpro.Model;

import java.time.LocalDate;
import java.util.Date;

public class Project {

    private int projectID;
    private String projectName;
    private LocalDate startDate;
    private LocalDate deadLine;
    private int totalTime;
    private String ProjectDescription;



    public Project (String projectName, String startDate,String deadLine, String projectDescription){
        this.projectName = projectName;
        this.startDate = LocalDate.parse(startDate);
        this.deadLine = LocalDate.parse(deadLine);
        this.ProjectDescription = projectDescription;
    }



    //************************* Setter and getters *******************************//





}
