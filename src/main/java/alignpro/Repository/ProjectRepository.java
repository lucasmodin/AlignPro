package alignpro.Repository;

import alignpro.Model.DBConnection;
import alignpro.Model.Projects.Project;
import alignpro.Repository.Interfaces.IProjectRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("PROJECT_REPOSITORY_JDBC")
@Lazy
public class ProjectRepository implements IProjectRepository {
    @Value("${spring.datasource.url}")
    private String dbURL;
    @Value("${spring.datasource.username}")
    private  String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    private Connection conn;

    public ProjectRepository() {}

    @PostConstruct
    @Override
    public void setConn() {
        this.conn = DBConnection.getConnection(dbURL,dbUsername,dbPassword);
    }




    //************************* Save Method *******************************//

    @Override
    public void saveProject(String projectName, String startDate,String deadLine, String projectDescription, int pmUserID){
        int primaryKeyProject = 0;
        String sqlString = "INSERT INTO Project (ProjectName, StartDate, Deadline, ProjectDescription) VALUES (?,?,?,?)";
        try{

            PreparedStatement stmt = conn.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, projectName);
            stmt.setString(2, startDate);
            stmt.setString(3, deadLine);
            stmt.setString(4, projectDescription);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()){
                primaryKeyProject = generatedKeys.getInt(1);
            }

            System.out.println("Generated key:" + primaryKeyProject);

            if(primaryKeyProject != 0){
                String sqlStringPM = "INSERT INTO PMUser_Project (PMUserID, ProjectID) VALUES (?, ?)";
                PreparedStatement stmtPM = conn.prepareStatement(sqlStringPM);
                stmtPM.setInt(1, pmUserID);
                stmtPM.setInt(2, primaryKeyProject);
                stmtPM.executeUpdate();
            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    //************************* Get by name Method *******************************//

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

    //************************* Get by ID Method *******************************//

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

    //************************* Edit Method *******************************//

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

    //************************* Delete method *******************************//

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
}
