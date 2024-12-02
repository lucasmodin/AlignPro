package alignpro.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class DBConnection {

    private static Connection conn;

    private DBConnection(){
    }

    public static Connection getConnection(String URL, String Admin, String Password){

        if (conn != null){return conn;}

        //(Connection connection = DriverManager.getConnection(URL, Admin, Password))

        try {
            Connection contemp = DriverManager.getConnection(URL,Admin,Password);
            conn = contemp;
        } catch (SQLException e) {
            System.out.println("not working");
            System.out.println("Kill me");

            throw new RuntimeException(e);
        }

        return conn;
    }

}