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

    //EMpty konstructor for now
    public TaskDTO(){}


}
