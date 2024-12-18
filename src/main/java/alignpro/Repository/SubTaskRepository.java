package alignpro.Repository;

import alignpro.Model.DBConnection;
import alignpro.Model.Projects.SubTask;
import alignpro.Repository.Interfaces.ISubTaskRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("SUBTASK_REPOSITORY_JDBC")
@Lazy
public class SubTaskRepository implements ISubTaskRepository {
    @Value("${spring.datasource.url}")
    private String dbURL;
    @Value("${spring.datasource.username}")
    private  String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    private Connection conn;

    public SubTaskRepository() {}

    @PostConstruct
    @Override
    public void setConn() {
        this.conn = DBConnection.getConnection(dbURL,dbUsername,dbPassword);
    }


    //************************* Save Method *******************************//

    @Override
    public int saveSubTask(String subTaskName, String startDate, String endDate,
                            int time, String subTaskDescription, String skillRequirement, int taskID) {
        int subTaskID = 0;
        String sqlString =
                "INSERT INTO SubTask (SubTaskName, StartDate, EndDate, EstimatedTime, SubTaskDescription, SkillRequirement, TaskID) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, subTaskName);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            stmt.setInt(4, time);
            stmt.setString(5, subTaskDescription);
            stmt.setString(6, skillRequirement);
            stmt.setInt(7, taskID);
            stmt.executeUpdate();

            ResultSet generatedKey = stmt.getGeneratedKeys();
            if (generatedKey.next()) {
                subTaskID = generatedKey.getInt(1);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subTaskID;
    }

    //************************* Get by ID Method *******************************//

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

    //************************* Get by name Method *******************************//


    //************************* Edit Method *******************************//

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

    //************************* Delete Method *******************************//

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

    @Override
    public void assignEmployeeToTask(int subtaskID, int employeeID) {

        try {
            String deleteSql = "DELETE FROM SubTask_Employee WHERE SubTaskID = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
            deleteStmt.setInt(1, subtaskID);
            deleteStmt.executeUpdate();

            String sqlString = "INSERT INTO Subtask_Employee (SubtaskID, EmployeeID) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, subtaskID);
            stmt.setInt(2, employeeID);
            stmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

}
