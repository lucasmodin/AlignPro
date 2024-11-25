package alignpro.Repository;

import alignpro.Model.Project;
import alignpro.Model.SubProject;

public interface IFAlignProRepository {

    void setConn();

    //Methods to manage project;
    void saveProject(String projectName, String startDate,String deadLine, String projectDescription);

    Project getProject(String projectName);

    Project getProject(int projectID);

    void editProject(Project project, int projectID);


    void saveSubProject(String subProjectName, String startDate, String endDate, String subProjectDescription, int projectID);

    SubProject getSubProject(String subProjectName);

    SubProject getSubProject(int subProjectID);
}
