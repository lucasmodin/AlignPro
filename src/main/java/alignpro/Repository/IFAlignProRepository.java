package alignpro.Repository;

import alignpro.Model.*;

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

    List<Task> getTaskForSubProject(int subProjectID);

    void saveSubProject(String subProjectName, String startDate, String endDate, String subProjectDescription, int projectID);

    SubProject getSubProject(String subProjectName);

    SubProject getSubProject(int subProjectID);

    Task getTask(int taskID);

    void deleteSubProject(int subProjectID);

    void editSubProject(SubProject subProject, int subProjectID);

    void editTask(Task task, int taskID);

    void editSubTask(SubTask subTask, int subTaskID);

    // Methods to manage Employees

    void saveEmployee(String employeeName);

    void saveEmployee(String employeeName, List<String> skillList);

    Employee getEmployee(String employeeName);

    List<Employee> getListOfEmployees();

    /// ***************************** Helper function to get infomration ************************* ///

    Map<String, Integer> getSkillsID();

    List<String> getListOfSkills();

    void deleteProject(int ProjectID);

    void deleteTask(int taskID);

    void saveTask(String taskName, String startDate, String endDate, int estimatedTime,
                         String taskDescription, String skillRequirement, int subProjectID);

    SubTask getSubTask(int subTaskID);

    void saveSubTask(String subTaskName, String startDate, String endDate,
                     int time, String subTaskDescription, String skillRequirement, int taskID);
}
