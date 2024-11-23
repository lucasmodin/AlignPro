package alignpro.Repository;


import alignpro.Model.DBConnection;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    /*
    @PostConstruct
    public void h2DBtest(){
        try{
            String sqlString = "INSERT INTO employee (EmployeeName) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, "Oskar August");
            stmt.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }


    }
    */


}
