package alignpro.Repository;

import alignpro.Model.DBConnection;
import alignpro.Model.Projects.Task;
import alignpro.Repository.Interfaces.ITaskRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("TASK_REPOSITORY_JDBC")
@Lazy
public class TaskRepository implements ITaskRepository {
    @Value("${spring.datasource.url}")
    private String dbURL;
    @Value("${spring.datasource.username}")
    private  String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    private Connection conn;

    public TaskRepository() {}

    @PostConstruct
    @Override
    public void setConn() {
        this.conn = DBConnection.getConnection(dbURL,dbUsername,dbPassword);
    }



    //************************* Save Method *******************************//

    @Override
    public void saveTask(String taskName, String startDate, String endDate,
                         String taskDescription, String skillRequirement, int subProjectID){
        String sqlString = "INSERT INTO Task (TaskName, StartDate, EndDate, EstimatedTime, TaskDescription, SkillRequirement, SubProjectID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, taskName);
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
            stmt.setInt(4, 0);
            stmt.setString(5, taskDescription);
            stmt.setString(6, skillRequirement);
            stmt.setInt(7, subProjectID);
            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //************************* Get by ID Method *******************************//

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

    //************************* Get by name Method *******************************//


    //************************* Edit Method *******************************//

    @Override
    public void editTask(Task task, int taskID){
        String sqlString = "UPDATE Task SET TaskName = ?, StartDate = ?, EndDate = ?," +
                " EstimatedTime = ?, TaskDescription = ?, SkillRequirement = ? WHERE TaskID = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, task.getTaskName());
            stmt.setString(2, task.getStartDateString());
            stmt.setString(3, task.getEndDateString());
            stmt.setInt(4, 0);
            stmt.setString(5, task.getTaskDescription());
            stmt.setString(6, task.getSkillRequirement());
            stmt.setInt(7, taskID);
            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Problem updating your task from the DB based on taskID" + e.getMessage());
        }
    }

    //************************* Delete Method *******************************//

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
}
