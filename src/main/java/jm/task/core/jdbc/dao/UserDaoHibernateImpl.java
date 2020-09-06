package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.*;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try( Session session = Util.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE User(id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(225) NOT NULL," +
                    "lastName VARCHAR(225) NOT NULL," +
                    "age TINYINT NOT NULL)").executeUpdate();
            tx1.commit();
            System.out.println("Таблица создана!");
        }
        catch(Throwable e){
            System.out.println("Таблица уже существует");
        }

    }

    @Override
    public void dropUsersTable() {
        try( Session session = Util.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.createSQLQuery("DROP TABLE User").executeUpdate();
            tx1.commit();
            System.out.println("Таблица удалена!");
        }
        catch(Throwable e){
            System.out.println(e);
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction tx1 = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx1.commit();
            System.out.println("User с именем - " + name + " Добавлен в базу данных");
        }
        catch(Throwable e){
            System.out.println(e);
        }

    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction tx1 = session.beginTransaction();
            User u1 = new User();
            u1.setId(id);
            session.delete(u1);
            tx1.commit();
            System.out.println("Удален пользователь под номером: " + id);
        }
        catch(Throwable e){
            System.out.println(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Session session = Util.getSessionFactory().openSession()){
            users = session.createQuery("FROM User").list();
        }
        catch(Throwable e){
            System.out.println(e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction tx1 = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            System.out.println("Таблица очищена");
        }
        catch(Throwable e){
            System.out.println(e);
        }
    }
}
