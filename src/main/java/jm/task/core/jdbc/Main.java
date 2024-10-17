package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("ilgiz1", "sab", (byte) 40);
        userService.saveUser("ilgiz2", "sab", (byte) 40);
        userService.saveUser("ilgiz3", "sab", (byte) 40);
        userService.saveUser("ilgiz4", "sab", (byte) 40);
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        //userService.removeUserById(5);
        //userService.cleanUsersTable();
        //userService.dropUsersTable();
    }
}
