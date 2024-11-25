package alignpro.Repository;


import alignpro.Model.DBConnection;
import alignpro.Model.Project;
import alignpro.Model.SubProject;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

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
    public void saveSubProject(String subProjectName, String startDate, String endDate, String subProjectDescription, int projectID){

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
    public void editProject(Project project, int projectID) {
        String sqlString = "UPDATE Project SET ProjectName = ?, StartDate = ?, Deadline = ?, ProjectDescription = ? WHERE ProjectID = ?";

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

    public List<Project> getPMDashboardData(int pmUserID) {
        List<Project> projects = new ArrayList<>();

        String sqlString = "SELECT p.ProjectID, p.ProjectName, p.StartDate AS ProjectStartDate, p.Deadline AS ProjectDeadline, p.ProjectDescription," +
                "sp.SubProjectID, sp.SubProjectName, sp.StartDate AS SubProjectStartDate, sp.EndDate AS SubProjectEndDate, sp.SubProjectDescription," +
                "t.TaskID, t.TaskName, t.StartDate AS TaskStartDate, t.EndDate AS TaskEndDate, t.TaskDescription," +
                "st.SubTaskID, st. SubTaskName, st.StartDate AS SubTaskStartDate, st.EndDate AS SubTaskEndDate, st.SubTaskDescription" +
                "FROM PMUser_Project pmup" +
                "JOIN Project p ON pmup.ProjectID = p.ProjectID" +
                "LEFT JOIN SubProject sp ON p.ProjectID = sp.ProjectID" +
                "LEFT JOIN Task t ON sp.SubProjectID = t.SubProjectID" +
                "LEFT JOIN SubTask st ON t.TaskID = st.TaskID" +
                "WHERE pmup.PMUserID = ?;";

        return null;
    }




}
