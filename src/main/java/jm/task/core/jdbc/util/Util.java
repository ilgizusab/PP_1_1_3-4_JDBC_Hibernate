package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kata1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";

    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection ERROR");
            e.printStackTrace();
        }
        return conn;
    }
}
