package alignpro.Repository.Interfaces;

import alignpro.Model.*;
import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubProject;
import alignpro.Model.Projects.SubTask;
import alignpro.Model.Projects.Task;

import java.util.List;
import java.util.Map;

public interface IFAlignProRepository {

    //void setConn();

    //pm dashboard methods

    List<SubProject> getSubProjectsForProject(int projectID);

    List<Project> getProjectsForPMUser(int pmUserID);

    List<Task> getTaskForSubProject(int subProjectID);

    List<SubTask> getSubTaskForTask(int taskID);




    // Methods to manage Employees

    public void editEmployee(Employee obj, int employeeID);

    void saveEmployee(String employeeName);

    void saveEmployee(String employeeName, List<String> skillList);

    Employee getEmployee(String employeeName);

    Employee getEmployee(int employeeID);

    void deleteEmployee(int employeeID);

    List<Employee> getListOfEmployees();

    /// ***************************** Helper function to get infomration ************************* ///

    Map<String, Integer> getSkillsID();

    List<String> getListOfSkills();


}
