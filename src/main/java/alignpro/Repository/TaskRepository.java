package alignpro.Repository;

import alignpro.Model.DBConnection;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

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
}
