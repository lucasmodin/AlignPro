package alignpro.Repository;


import alignpro.Model.*;
import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubProject;
import alignpro.Model.Projects.SubTask;
import alignpro.Model.Projects.Task;
import alignpro.Repository.Interfaces.IFAlignProRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("ALIGNPRO_REPOSITORY_JDBC")
@Lazy
public class AlignProRepository implements IFAlignProRepository {

    @Value("${spring.datasource.url}")
    private String dbURL;
    @Value("${spring.datasource.username}")
    private  String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    private Connection conn;

    /// ********************************* Constructor and set-up ************************** ///
    public AlignProRepository(){
    }

    //PostConstruct runes functions after we have generated the constructor
    //remove problem with beans.

    @PostConstruct
    @Override
    public void setConn() {
        this.conn = DBConnection.getConnection(dbURL,dbUsername,dbPassword);
    }


    //Methods to manage employees;
    @Override
    public void saveEmployee(String employeeName){
        try{
            String sqlString = "INSERT INTO Employee (EmployeeName) VALUES ?";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1,employeeName);

            stmt.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Could not save employee" + e.getMessage());
        }
    }

    @Override
    public void saveEmployee(String employeeName, List<String> skillList){
        int primaryKeyEmployee = 0;

        try{

            String sqlString = "INSERT INTO Employee (EmployeeName) VALUES ?";

            PreparedStatement stmt = conn.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, employeeName);
            stmt.executeUpdate();

            ResultSet returnedPrimaryKey = stmt.getGeneratedKeys();

            if (returnedPrimaryKey.next()){
                primaryKeyEmployee = returnedPrimaryKey.getInt(1);
            }

            Map<String, Integer> pariList = getSkillsID();

            if (!skillList.isEmpty()){
                for (String skill : skillList ){

                    String sqlString2 = "INSERT INTO Employee_Skill (EmployeeID, SkillID) VALUES (?, ?)";

                    PreparedStatement stmt2 = conn.prepareStatement(sqlString2);
                    stmt2.setInt(1, primaryKeyEmployee);
                    stmt2.setInt(2, pariList.get(skill));
                    stmt2.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not save employee with a skill list" + e.getMessage());
        }
    }

    @Override // This method should perhaps also have the list of Skills
    public Employee getEmployee(String employeeName){
        Employee employee = null;

        try{

            String sqlString = "SELECT * FROM Employee WHERE EmployeeName = ?";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1,employeeName);

            ResultSet rls = stmt.executeQuery();

            if(rls.next()){
                employee = new Employee();
                employee.setEmployeeID(rls.getInt(1));
                employee.setEmployeeName(rls.getString(2));
            } else {
                return employee;
            }

            String sqlString2 = "SELECT Skills.SkillName from Employee_Skill JOIN Skills on Employee_Skill.SkillID = Skills.SkillID WHERE Employee_Skill.EmployeeID = ?";

            PreparedStatement stmt2 = conn.prepareStatement(sqlString2);
            stmt2.setInt(1,employee.getEmployeeID());

            ResultSet rls2 = stmt2.executeQuery();

            while(rls2.next()){
                employee.setSkills(rls2.getString(1));
            }

        } catch (SQLException e){
            throw new RuntimeException("Get an employee is not working" + e.getMessage());
        }

        return employee;

}

    @Override
    public Employee getEmployee(int employeeID)
    {    Employee employee = null;
        try{
            String sqlString = "SELECT * FROM Employee WHERE EmployeeID = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1,employeeID);
            ResultSet rls = stmt.executeQuery();
            if(rls.next()){
                employee = new Employee();
                employee.setEmployeeID(rls.getInt(1));
                employee.setEmployeeName(rls.getString(2));
            } else {
                return employee;
            }
            String sqlString2 = "SELECT Skills.SkillName from Employee_Skill JOIN Skills on Employee_Skill.SkillID = Skills.SkillID WHERE Employee_Skill.EmployeeID = ?";
            PreparedStatement stmt2 = conn.prepareStatement(sqlString2);
            stmt2.setInt(1,employee.getEmployeeID());
            ResultSet rls2 = stmt2.executeQuery();
            while(rls2.next()){
                employee.setSkills(rls2.getString(1));
            }
        } catch (SQLException e){
            throw new RuntimeException("Get an employee is not working" + e.getMessage());
        }
        return employee;
    }



    @Override
    public List<Employee> getListOfEmployees(){
        List<String> employeeListName = new ArrayList<>();
        List<Employee> employeeList = new ArrayList<>();

        try {

            String sqlString = "SELECT EmployeeName FROM Employee";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            ResultSet rls = stmt.executeQuery();

            while(rls.next()){
                String employeeName = rls.getString(1);
                employeeListName.add(employeeName);
            }

            for (String employeeName: employeeListName){
                employeeList.add(getEmployee(employeeName));
            }

        } catch (SQLException e){
            throw new RuntimeException("Could not get list of emplyees" + e.getMessage());
        }

        return employeeList;
    }

    @Override
    public void deleteEmployee(int employeeID){
        String sqlString = "DELETE FROM Employee WHERE EmployeeID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, employeeID);
            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("Not deleting employee from DB" + e.getMessage());
        }
    }

    @Override
    public void editEmployee(Employee obj, int employeeID){

        try{
            String sqlString = "UPDATE Employee SET EmployeeName = ? Where EmployeeID = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, obj.getEmployeeName());
            stmt.setInt(2,employeeID);
            stmt.executeUpdate();


            Map<String, Integer> pairList = getSkillsID();

            String sqlDeleteSkills ="DELETE FROM Employee_Skill WHERE EmployeeID = ?";
            PreparedStatement stmt2 = conn.prepareStatement(sqlDeleteSkills);
            stmt2.setInt(1,employeeID);
            stmt2.executeUpdate();

            for(String skills : obj.getSkills()){

                String sqlSetSkills = "INSERT INTO Employee_Skill (EmployeeID, SkillID) VALUES (?, ?)";

                PreparedStatement stmt3 = conn.prepareStatement(sqlSetSkills);
                stmt3.setInt(1, employeeID);
                stmt3.setInt(2, pairList.get(skills));
                stmt3.executeUpdate();
            }

        } catch (SQLException e){
            throw new RuntimeException("not updating emplyee information" + e.getMessage());
        }

    }


    /// ***************************** Helper function to get infomration ************************* ///

    @Override
    public Map<String, Integer> getSkillsID(){
        Map<String, Integer> pairList = new HashMap<>();

        try{

            String sqlString = "SELECT * FROM Skills";
            PreparedStatement stmt = conn.prepareStatement(sqlString);

            ResultSet rls = stmt.executeQuery();

            while(rls.next()){
                String skillName = rls.getString("SkillName");
                int skillID = rls.getInt("SkillID");

                pairList.put(skillName, skillID);
            }

        } catch (SQLException e){
            throw new RuntimeException("it failed in helper function getSkillsID" + e.getMessage());
        }

        return pairList;
    }

    // *** Thymeleaf functions to get lists from DB *** //

    @Override
    public List<String> getListOfSkills(){
        List<String> list = new ArrayList<>();

        try{

            String sqlString = "SELECT SkillName FROM Skills";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            ResultSet rls = stmt.executeQuery();

            while (rls.next()){
                list.add(rls.getString("SkillName"));
            }

        } catch (SQLException e){
            throw new RuntimeException("Could not get list of skills" + e.getMessage());
        }
        return list;
    }


    //**This methods do I overload, so I can get all projects, subprojects, task and subtask. **//
    @Override
    public List<Project> getProjectsForPMUser(int pmUserID) {
        List<Project> projects = new ArrayList<>();
        String sql = """
                SELECT p.ProjectID AS ProjectID, p.ProjectName AS ProjectName, p.StartDate AS StartDate, p.Deadline AS Deadline, p.TotalSumTime AS TotalSumTime, p.ProjectDescription AS ProjectDescription
                FROM Project p
                JOIN PMUser_Project pmup on p.ProjectID = pmup.ProjectID
                WHERE pmup.PMUserID = ?;
                """;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pmUserID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("ProjectID"));
                project.setProjectName(rs.getString("ProjectName"));
                project.setStartDate(rs.getString("StartDate"));
                project.setDeadLine(rs.getString("Deadline"));
                project.setTotalTime(rs.getInt("TotalSumTime"));
                project.setProjectDescription(rs.getString("ProjectDescription"));
                projects.add(project);
            }


        } catch(SQLException e) {
            throw new RuntimeException(e);
        }

        return projects;
    }


    //**overload functions there just returns the full list of projects, subprojects, task and subtask.

    @Override
    public List<Project> getProjectsForPMUser() {
        List<Project> projects = new ArrayList<>();
        String sql = """
                SELECT p.ProjectID AS ProjectID, p.ProjectName AS ProjectName, p.StartDate AS StartDate, p.Deadline AS Deadline, p.TotalSumTime AS TotalSumTime, p.ProjectDescription AS ProjectDescription
                FROM Project p
                JOIN PMUser_Project pmup on p.ProjectID = pmup.ProjectID;
                """;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);


            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("ProjectID"));
                project.setProjectName(rs.getString("ProjectName"));
                project.setStartDate(rs.getString("StartDate"));
                project.setDeadLine(rs.getString("Deadline"));
                project.setTotalTime(rs.getInt("TotalSumTime"));
                project.setProjectDescription(rs.getString("ProjectDescription"));
                projects.add(project);
            }


        } catch(SQLException e) {
            throw new RuntimeException(e);
        }

        return projects;
    }

    @Override
    public List<SubProject> getSubProjectsForProject() {
        List<SubProject> subProjects = new ArrayList<>();
        String sql = """
                SELECT SubprojectID, SubProjectName, StartDate, EndDate, SumTime, SubProjectDescription, ProjectID
                FROM SubProject;
                """;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SubProject subProject = new SubProject();
                subProject.setSubProjectID(rs.getInt("SubprojectID"));
                subProject.setSubProjectName(rs.getString("SubProjectName"));
                subProject.setStartDate(rs.getString("StartDate"));
                subProject.setEndDate(rs.getString("EndDate"));
                subProject.setFkProjectID(rs.getInt("ProjectID"));
                subProject.setSubProjectDescription(rs.getString("SubProjectDescription"));
                subProjects.add(subProject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subProjects;
    }

    @Override
    public List<Task> getTaskForSubProject(){
        List<Task> tasks = new ArrayList<>();
        String sqlString = """
                SELECT TaskID, TaskName, StartDate, EndDate, EstimatedTime, TaskDescription, SkillRequirement, SubProjectID
                FROM Task;
                """;

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setTaskID(rs.getInt("TaskID"));
                task.setTaskName(rs.getString("TaskName"));
                task.setStartDateString(rs.getString("StartDate"));
                task.setEndDateString(rs.getString("EndDate"));
                task.setEstimatedTime(rs.getInt("EstimatedTime"));
                task.setTaskDescription(rs.getString("TaskDescription"));
                task.setSkillRequirement(rs.getString("SkillRequirement"));
                task.setSubProjectID(rs.getInt("SubProjectID"));
                tasks.add(task);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public List<SubTask> getSubTaskForTask(){
        List<SubTask> subTasks = new ArrayList<>();
        String sqlString = """
                SELECT SubTaskID, SubTaskName, StartDate, EndDate, EstimatedTime, SubTaskDescription, SkillRequirement, TaskID
                FROM SubTask;
                """;

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SubTask subTask = new SubTask();
                subTask.setSubTaskID(rs.getInt("SubTaskID"));
                subTask.setSubTaskName(rs.getString("SubTaskName"));
                subTask.setStartDate(rs.getString("StartDate"));
                subTask.setEndDate(rs.getString("EndDate"));
                subTask.setTime(rs.getInt("EstimatedTime"));
                subTask.setSubTaskDescription(rs.getString("SubTaskDescription"));
                subTask.setSkillRequirement(rs.getString("SkillRequirement"));
                subTask.setTaskID(rs.getInt("TaskID"));
                subTasks.add(subTask);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return subTasks;
    }


    /// ***************************** Mapping function to get DTO-Object ************************* ///

    public List<Integer> pmUserProjectID(int pmUserID){
        List<Integer> listofProjectIDS = new ArrayList<>();


        try{

            String sqlString = "SELECT (ProjectID) FROM PMUser_Project WHERE PMUserID = ?";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1,pmUserID);

            ResultSet rls = stmt.executeQuery();

            while(rls.next()){
                listofProjectIDS.add(rls.getInt("ProjectID"));
            }

        } catch(SQLException e){
            throw new RuntimeException("could not match User to Projects" + e.getMessage());
        }

        return listofProjectIDS;
    }

    //TODO THis create map with all subprojets, task and subtask, linked to ProjectName
    public Map<String,String> projectNamesToSubprojectandTask(int projectID) {
        String sqlString = "SELECT p.ProjectName, sp.SubProjectName, t.TaskName, st.SubTaskName FROM Project p LEFT JOIN SubProject sp ON p.ProjectID = sp.ProjectID LEFT JOIN Task t ON sp.SubProjectID = t.SubProjectID LEFT JOIN SubTask st ON t.TaskID = st.TaskID WHERE p.ProjectID = ? ORDER BY p.ProjectName, sp.SubProjectName, t.TaskName, st.SubTaskName;";

        Map<String, String> mapping = new HashMap<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1,projectID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String projectName = resultSet.getString("ProjectName");

                // Map SubProjectName to ProjectName
                String subProjectName = resultSet.getString("SubProjectName");
                if (subProjectName != null) {
                    mapping.put(subProjectName, projectName);
                }

                // Map TaskName to ProjectName
                String taskName = resultSet.getString("TaskName");
                if (taskName != null) {
                    mapping.put(taskName, projectName);
                }

                // Map SubTaskName to ProjectName
                String subTaskName = resultSet.getString("SubTaskName");
                if (subTaskName != null) {
                    mapping.put(subTaskName, projectName);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Not match project name to subproject, task and subtask" + e.getMessage());
        }

        return mapping;
    }









}

