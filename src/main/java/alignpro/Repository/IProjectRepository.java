package alignpro.Repository;

import alignpro.Model.Project;

public interface IProjectRepository {

    void setConn();

    void saveProject(String projectName, String startDate,String deadLine, String projectDescription);

    Project getProject(String projectName);

    Project getProject(int projectID);

    void editProject(Project project, int projectID);

    void deleteProject(int ProjectID);

}
