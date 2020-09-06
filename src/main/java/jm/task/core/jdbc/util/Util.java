package jm.task.core.jdbc.util;


import org.hibernate.SessionFactory;
import jm.task.core.jdbc.model.User;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;




public class Util {
    // реализуйте настройку соеденения с БД
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.dialect" , "org.hibernate.dialect.MySQL5Dialect")
                    .setProperty("hibernate.connection.driver_class" , "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url" , "jdbc:mysql://localhost:3306/mydb")
                    .setProperty("hibernate.connection.username" , "root")
                    .setProperty("hibernate.connection.password" , "admin")
                    .setProperty("show_sql" , "true")
                    .setProperty("hibernate.hbm2ddl.auto" , "update");
                    configuration.addAnnotatedClass(User.class);
            try {

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;

    }



    public static Connection getMySQlConnection() throws SQLException{
        String userName = "root";
        String password = "admin";
        return getMySQlConnection(userName, password);
    }

    public static Connection getMySQlConnection(String userName, String password) throws SQLException {
        //Class.forName("com.mysql.jdbc.Driver");

        String connectionURL = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

}
