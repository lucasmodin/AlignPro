package alignpro.Repository.Interfaces;

import alignpro.Model.Projects.SubProject;

public interface ISubProjectRepository {

    void setConn();

    void saveSubProject(String subProjectName, String startDate, String endDate, String subProjectDescription, int projectID);

    SubProject getSubProject(String subProjectName);

    SubProject getSubProject(int subProjectID);

    void deleteSubProject(int subProjectID);

    void editSubProject(SubProject subProject, int subProjectID);
}
