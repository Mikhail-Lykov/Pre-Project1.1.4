package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            executeSQLStatement("CREATE TABLE User(id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    " name VARCHAR(225) NOT NULL," +
                    " lastName VARCHAR(225) NOT NULL," +
                    " age TINYINT NOT NULL)", "Создана таблица");
        }
        catch(SQLException e){
            System.out.println("Таблица уже существует!");
        }

    }

    public void dropUsersTable() {
        try {
            executeSQLStatement("DROP TABLE User",
                    "Таблица удалена");
        }
        catch(SQLException e){
            System.out.println("Таблицы не существует!");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            executeSQLStatement("INSERT User(name, lastName, age) VALUES ('" +
                            name + "', '" + lastName + "', '" + age + "')",
                    "User с именем - " + name + " Добавлен в базу данных");
        }
        catch(SQLException e){
            System.err.println(e);
        }

    }

    public void removeUserById(long id) {
        try {
            executeSQLStatement("DELETE FROM User WHERE id = " + id,
                    "Удален пользователь под номером: " + id);
        }
        catch(SQLException e){
            System.err.println(e);
        }

    }

    public List<User> getAllUsers() {
        try(Connection  conn = Util.getMySQlConnection()) {
            Statement statement = conn.createStatement();
            String sql = "SELECT name, lastName, age FROM User";
            ResultSet rs = statement.executeQuery(sql);
            List<User> list = new ArrayList<>();
            while(rs.next()){
                list.add(new User(rs.getString("name"), rs.getString("lastName"), (byte)rs.getByte("age")));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            executeSQLStatement("DELETE FROM User",
                    "Таблица очищена");
        }
        catch(SQLException e){
            System.out.println("Таблицы не существует!");
        }
    }

    public void executeSQLStatement(String sql, String description) throws SQLException {
        Connection conn = Util.getMySQlConnection();
        Statement statement = conn.createStatement();
        statement.execute(sql);
        statement.close();
        System.out.println(description);

    }
}
