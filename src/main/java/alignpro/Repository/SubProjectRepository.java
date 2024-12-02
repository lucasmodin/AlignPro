package alignpro.Repository;

import alignpro.Model.DBConnection;
import alignpro.Model.Projects.SubProject;
import alignpro.Repository.Interfaces.ISubProjectRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("SUBPROJECT_REPOSITORY_JDBC")
@Lazy
public class SubProjectRepository implements ISubProjectRepository {
    @Value("${spring.datasource.url}")
    private String dbURL;
    @Value("${spring.datasource.username}")
    private  String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    private Connection conn;

    public SubProjectRepository() {}

    @PostConstruct
    @Override
    public void setConn() {
        this.conn = DBConnection.getConnection(dbURL,dbUsername,dbPassword);
    }


    //************************* Save Method *******************************//

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

    //************************* Get by ID Method *******************************//

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

    //************************* Get by name Method *******************************//

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

    //************************* Edit Method *******************************//

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

    //************************* Delete Method *******************************//

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
}
