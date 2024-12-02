package alignpro.Model.DTOModel;

import java.time.LocalDate;

public class SubTaskDTO {

    //TODO this is the filter string it needs to learn its project.
    //TODO write logic there makes SubTask knows it project
    private String filter;

    private String subTaskName;
    private String subTaskDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private int time;

    //Empty konstrucot
    public SubTaskDTO(){}

}
