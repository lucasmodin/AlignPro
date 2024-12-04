package alignpro.Repository.Interfaces;

import alignpro.Model.Projects.Project;

public interface IProjectRepository {

    void setConn();

    void saveProject(String projectName, String startDate,String deadLine, String projectDescription, int pmUserID);

    Project getProject(String projectName);

    Project getProject(int projectID);

    void editProject(Project project, int projectID);

    void deleteProject(int ProjectID);

}
