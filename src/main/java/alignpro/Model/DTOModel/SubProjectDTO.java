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

}
