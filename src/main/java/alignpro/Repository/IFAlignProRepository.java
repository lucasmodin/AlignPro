package alignpro.Repository;

import alignpro.Model.Project;

public interface IFAlignProRepository {

    void setConn();

    //Methods to manage project;
    void saveProject(String projectName, String startDate,String deadLine, String projectDescription);

    Project getProject(String projectName);

    Project getProject(int ProjectID);

    //void editProject();


    void saveSubProject(String subProjectName, String startDate, String endDate, String subProjectDescription);
}
