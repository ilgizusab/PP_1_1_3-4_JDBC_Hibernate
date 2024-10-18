package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userService = new UserServiceImpl();

        // Создание таблицы User(ов)
        userService.createUsersTable();

        //Добавление 4 User(ов) в таблицу с данными на свой выбор.
        for (int i = 0; i < 4; i++) {
            String username = "user" + i;
            userService.saveUser(username, "sab", (byte) 40);
        }

        //Получение всех User из базы и вывод в консоль
        List<User> users = userService.getAllUsers();
        long maxUserID = 0;
        for (User user : users) {
            System.out.println(user);
            if (user.getId() > maxUserID) {maxUserID = user.getId();}
        }

        //Удаление User из таблицы (по id)
        userService.removeUserById(maxUserID);

        //Очистка содержания таблицы
        userService.cleanUsersTable();

        //Удаление таблицы User(ов)
        userService.dropUsersTable();
    }
}
