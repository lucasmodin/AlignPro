package alignpro.Repository;

import alignpro.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IFAlignProRepository {

    void setConn();

    //pm dashboard methods

    List<SubProject> getSubProjectsForProject(int projectID);

    List<Project> getProjectsForPMUser(int pmUserID);

    List<Task> getTaskForSubProject(int subProjectID);






    // Methods to manage Employees

    void saveEmployee(String employeeName);

    void saveEmployee(String employeeName, List<String> skillList);

    Employee getEmployee(String employeeName);

    List<Employee> getListOfEmployees();

    /// ***************************** Helper function to get infomration ************************* ///

    Map<String, Integer> getSkillsID();

    List<String> getListOfSkills();


}
