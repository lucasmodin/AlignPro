package alignpro.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//By using the component annotation we ensure spring see this class as a bean that needs to be loaded
@Component
public class DBConnection {

    private Connection conn;

    //instead of doing the application values in each repo we just pass them here
    public DBConnection(@Value("${spring.datasource.url}") String URL,
                        @Value("${spring.datasource.username}") String Admin,
                        @Value("${spring.datasource.password}") String Password){
        try {
            this.conn = DriverManager.getConnection(URL, Admin, Password);
        }catch (SQLException e){
            System.out.println("Failing at getting connection " + e.getMessage());
            throw new RuntimeException("Failing at getting connection to the database" + e.getMessage());
        }
    }

    public Connection getConnection() {
        return this.conn;
    }
}
