package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Михаил", "Лермонтов", (byte) 26);
        user.saveUser("Александр", "Пушкин", (byte) 37);
        user.saveUser("Михаил", "Ломоносов", (byte) 53);
        user.saveUser("Николай", "Гоголь", (byte) 42);
        List<User> list = user.getAllUsers();
        for(Object a : list){
            System.out.println(a);
        }
        user.cleanUsersTable();
        user.dropUsersTable();


    }
}
