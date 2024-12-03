package alignpro.Repository;

import alignpro.Model.DBConnection;
import alignpro.Model.ProjectManager;
import alignpro.Repository.Interfaces.ILoginController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("LOGIN_REPOSITORY_JDBC")
@Lazy
public class LoginRepository implements ILoginController {

    @Value("${spring.datasource.url}")
    private String dbURL;
    @Value("${spring.datasource.username}")
    private  String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    private Connection conn;

    public LoginRepository() {

    }

    @PostConstruct
    @Override
    public void setConn() {
        this.conn = DBConnection.getConnection(dbURL,dbUsername,dbPassword);
    }

    @Override
    public ProjectManager getProjectManager(String mail) {
        ProjectManager pm = null;
        String sqlString = """
                SELECT PMUserID, FullName, Mail, PMPassword FROM PMUser WHERE LOWER(Mail) = ?
                """;

        try {
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, mail.toLowerCase());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                int userID = rs.getInt("PMUserID");
                String fullName = rs.getString("FullName");
                String userMail = rs.getString("Mail");
                String password = rs.getString("PMPassword");

                if (pm == null) {
                    pm = new ProjectManager(userID,fullName,userMail,password);
                }
            }

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        return pm;
    }
}
