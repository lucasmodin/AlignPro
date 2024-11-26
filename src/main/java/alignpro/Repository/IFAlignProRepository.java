package alignpro.Repository;

import alignpro.Model.Employee;
import alignpro.Model.Project;
import alignpro.Model.SubProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IFAlignProRepository {

    void setConn();

    //Methods to manage project;
    void saveProject(String projectName, String startDate,String deadLine, String projectDescription);

    Project getProject(String projectName);

    Project getProject(int projectID);

    void editProject(Project project, int projectID);

    List<SubProject> getSubProjectsForProject(int projectID);

    List<Project> getProjectsForPMUser(int pmUserID);

    void saveSubProject(String subProjectName, String startDate, String endDate, String subProjectDescription, int projectID);

    SubProject getSubProject(String subProjectName);

    SubProject getSubProject(int subProjectID);

    void deleteSubProject(int subProjectID);

    void editSubProject(SubProject subProject, int subProjectID);
    // Methods to manage Employees

    void saveEmployee(String employeeName);

    void saveEmployee(String employeeName, List<String> skillList);

    Employee getEmployee(String employeeName);

    List<Employee> getListOfEmployees();

    /// ***************************** Helper function to get infomration ************************* ///

    Map<String, Integer> getSkillsID();

    List<String> getListOfSkills();

    void deleteProject(int ProjectID);

//    void saveTask(String taskName, String startDate, String endDate, int estimatedTime,
//                         String taskDescription, String skillRequirement, int subProjectID);

}
