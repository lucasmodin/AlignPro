package alignpro.Repository;


import alignpro.Model.*;
import jakarta.annotation.PostConstruct;
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
import java.util.ArrayList;
import java.util.List;

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

    //Methods to manage project;

    @Override
    public void saveProject(String projectName, String startDate,String deadLine, String projectDescription){

        try{

            String sqlString = "INSERT INTO Project (ProjectName, StartDate, Deadline, ProjectDescription) VALUES (?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, projectName);
            stmt.setString(2, startDate);
            stmt.setString(3, deadLine);
            stmt.setString(4, projectDescription);
            stmt.executeUpdate();


        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project getProject(String projectName){
        Project obj = null;

        try{
            String sqlString = "SELECT ProjectID, ProjectName, StartDate, Deadline, ProjectDescription FROM Project WHERE ProjectName = ?";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1,projectName);

            ResultSet rls = stmt.executeQuery();
            if(rls.next()){
                obj = new Project();
                obj.setProjectID(rls.getInt("ProjectID"));
                obj.setProjectName(rls.getString("ProjectName"));
                obj.setStartDate(rls.getString("StartDate"));
                obj.setDeadLine(rls.getString("Deadline"));
                obj.setProjectDescription(rls.getString("ProjectDescription"));
            }

        } catch (SQLException e){
            throw new RuntimeException("Problem getting your project from the DB based on Projectname" + e.getMessage());
        }

        return obj;
    }

    @Override
    public Project getProject(int projectID){
        Project obj = null;

        try{
            String sqlString = "SELECT ProjectID, ProjectName, StartDate, Deadline, ProjectDescription FROM Project WHERE ProjectID = ?";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1,projectID);

            ResultSet rls = stmt.executeQuery();
            if(rls.next()){
                obj = new Project();
                obj.setProjectID(rls.getInt("ProjectID"));
                obj.setProjectName(rls.getString("ProjectName"));
                obj.setStartDate(rls.getString("StartDate"));
                obj.setDeadLine(rls.getString("Deadline"));
                obj.setProjectDescription(rls.getString("ProjectDescription"));
            }

        } catch (SQLException e){
            throw new RuntimeException("Problem getting your project from the DB based on ProjectID" + e.getMessage());
        }

        return obj;
    }

    @Override
    public void saveSubProject(String subProjectName, String startDate, String endDate,
                               String subProjectDescription, int projectID){

        try{
            String sqlString = "INSERT INTO SubProject (SubProjectName, StartDate, EndDate, SubProjectDescription, ProjectID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, subProjectName);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            stmt.setString(4, subProjectDescription);
            stmt.setInt(5, projectID);
            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveTask(String taskName, String startDate, String endDate, int estimatedTime,
                String taskDescription, String skillRequirement, int subProjectID){
        String sqlString = "INSERT INTO Task (TaskName, StartDate, EndDate, EstimatedTime, TaskDescription, SkillRequirement, SubProjectID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, taskName);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            stmt.setInt(4, estimatedTime);
            stmt.setString(5, taskDescription);
            stmt.setString(6, skillRequirement);
            stmt.setInt(7, subProjectID);
            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public SubProject getSubProject(String subProjectName){
        SubProject obj = null;

        try{
            String sqlString = "SELECT SubProjectID, SubProjectName, StartDate, EndDate, SubProjectDescription FROM SubProject WHERE SubProjectName = ?";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, subProjectName);

            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                obj = new SubProject();
                obj.setSubProjectID(resultSet.getInt("SubProjectID"));
                obj.setSubProjectName(resultSet.getString("SubProjectName"));
                obj.setStartDate(resultSet.getString("StartDate"));
                obj.setEndDate(resultSet.getString("EndDate"));
                obj.setSubProjectDescription(resultSet.getString("SubProjectDescription"));
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return obj;
    }


    @Override
    public SubProject getSubProject(int subProjectID) {
        SubProject obj = null;

        try {
            String sqlString = "SELECT SubProjectID, SubProjectName, StartDate, EndDate, SubProjectDescription FROM SubProject WHERE SubProjectID = ?";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, subProjectID);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                obj = new SubProject();
                obj.setSubProjectID(resultSet.getInt("SubProjectID"));
                obj.setSubProjectName(resultSet.getString("SubProjectName"));
                obj.setStartDate(resultSet.getString("StartDate"));
                obj.setEndDate(resultSet.getString("EndDate"));
                obj.setSubProjectDescription(resultSet.getString("SubProjectDescription"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    @Override
    public Task getTask(int taskID){
        Task task = null;

        String sqlString = "SELECT TaskID, TaskName, StartDate, EndDate, EstimatedTime, TaskDescription, SkillRequirement FROM Task WHERE TaskID = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, taskID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                task = new Task();
                task.setTaskID(rs.getInt(1));
                task.setTaskName(rs.getString(2));
                task.setStartDateString(rs.getString(3));
                task.setEndDateString(rs.getString(4));
                task.setEstimatedTime(rs.getInt(5));
                task.setTaskDescription(rs.getString(6));
                task.setSkillRequirement(rs.getString(7));
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return task;
    }

    @Override
    public void editTask(Task task, int taskID){
        String sqlString = "UPDATE Task SET TaskName = ?, StartDate = ?, EndDate = ?," +
                " EstimatedTime = ?, TaskDescription = ?, SkillRequirement = ? WHERE TaskID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, task.getTaskName());
            stmt.setString(2, task.getStartDateString());
            stmt.setString(3, task.getEndDateString());
            stmt.setInt(4, task.getEstimatedTime());
            stmt.setString(5, task.getTaskDescription());
            stmt.setString(6, task.getSkillRequirement());
            stmt.setInt(7, taskID);
            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Problem updating your task from the DB based on taskID" + e.getMessage());
        }
    }

    @Override
    public void editProject(Project project, int projectID) {
        String sqlString = "UPDATE Project SET ProjectName = ?, StartDate = ?, Deadline = ?," +
                " ProjectDescription = ? WHERE ProjectID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, project.getProjectName());
            stmt.setString(2, project.getStartDateString());
            stmt.setString(3, project.getDeadlineString());
            stmt.setString(4, project.getProjectDescription());
            stmt.setInt(5, projectID);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Problem updating your project from the DB based on ProjectID" + e.getMessage());
        }

    }

    @Override
    public void editSubProject(SubProject subProject, int subProjectID){
        String sqlString = "UPDATE SubProject SET SubProjectName = ?, StartDate = ?," +
                " EndDate = ?, SubProjectDescription = ? WHERE SubProjectID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, subProject.getSubProjectName());
            stmt.setString(2, subProject.getStartDateString());
            stmt.setString(3, subProject.getEndDateString());
            stmt.setString(4, subProject.getSubProjectDescription());
            stmt.setInt(5, subProjectID);

            stmt.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Problem updating your sub-project from the DB based on subProjectID/ProjectID" + e.getMessage());
        }
    }

    public void editSubTask(SubTask subTask, int subTaskID){
        String sqlString = "UPDATE SubTask SET SubTaskName = ?, StartDate = ?," +
                " EndDate = ?, EstimatedTime = ?, SubTaskDescription = ?, SkillRequirement = ? WHERE SubTaskID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, subTask.getSubTaskName());
            stmt.setString(2, subTask.getStartDateString());
            stmt.setString(3, subTask.getEndDateString());
            stmt.setInt(4, subTask.getTime());
            stmt.setString(5, subTask.getSubTaskDescription());
            stmt.setString(6, subTask.getSkillRequirement());
            stmt.setInt(7, subTaskID);

            stmt.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Problem updating your sub-task from the DB based on subTaskID/TaskID" + e.getMessage());
        }
    }

    public void deleteSubProject(int subProjectID){
        try{
            String sqlString = "DELETE FROM SubProject WHERE SubProjectID = ?";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, subProjectID);
            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteProject(int ProjectID){
        String sqlString = "DELETE FROM Project WHERE ProjectID = ?";

        try{

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1,ProjectID);
            stmt.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException("Not deleting project from DB" + e.getMessage());
        }
    }

    @Override
    public void deleteTask(int taskID){
        String sqlString = "DELETE FROM Task WHERE TaskID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, taskID);
            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("Not deleting task from DB" + e.getMessage());
        }
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

    @Override
    public List<SubProject> getSubProjectsForProject(int projectID) {
        List<SubProject> subProjects = new ArrayList<>();
        String sql = """
                SELECT SubprojectID, SubProjectName, StartDate, EndDate, SumTime, SubProjectDescription, ProjectID
                FROM SubProject
                WHERE ProjectID = ?;
                """;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, projectID);

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
    public List<Task> getTaskForSubProject(int subProjectID){
        List<Task> tasks = new ArrayList<>();
        String sqlString = """
                SELECT TaskID, TaskName, StartDate, EndDate, EstimatedTime, TaskDescription, SkillRequirement, SubProjectID
                FROM Task
                WHERE SubProjectID = ?;
                """;

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, subProjectID);
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
    public SubTask getSubTask(int subTaskID){
        String sqlString =
                "SELECT SubTaskID, SubTaskName, StartDate, EndDate, EstimatedTime, SubTaskDescription, SkillRequirement FROM SubTask WHERE SubTaskID = ?";
        SubTask subTask = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, subTaskID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                subTask = new SubTask();
                subTask.setSubTaskID(rs.getInt("SubTaskID"));
                subTask.setSubTaskName(rs.getString("SubTaskName"));
                subTask.setStartDate(rs.getString("StartDate"));
                subTask.setEndDate(rs.getString("EndDate"));
                subTask.setTime(rs.getInt("EstimatedTime"));
                subTask.setSubTaskDescription(rs.getString("SubTaskDescription"));
                subTask.setSkillRequirement(rs.getString("SkillRequirement"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subTask;
    }

    @Override
    public void saveSubTask(String subTaskName, String startDate, String endDate,
                            int time, String subTaskDescription, String skillRequirement, int taskID) {

        String sqlString =
                "INSERT INTO SubTask (SubTaskName, StartDate, EndDate, EstimatedTime, SubTaskDescription, SkillRequirement, TaskID) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, subTaskName);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            stmt.setInt(4, time);
            stmt.setString(5, subTaskDescription);
            stmt.setString(6, skillRequirement);
            stmt.setInt(7, taskID);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public void deleteSubTask(int subTaskID) {
        String sqlString =
                "DELETE FROM SubTask WHERE SubTaskID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, subTaskID);
            stmt.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }









}

