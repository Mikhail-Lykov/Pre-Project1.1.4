package jm.task.core.jdbc.util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getMySQlConnection() throws SQLException{
        String hostName = "localhost";
        String dbName = "mydb";
        String userName = "root";
        String password = "admin";
        return getMySQlConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQlConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException {
        //Class.forName("com.mysql.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

}
