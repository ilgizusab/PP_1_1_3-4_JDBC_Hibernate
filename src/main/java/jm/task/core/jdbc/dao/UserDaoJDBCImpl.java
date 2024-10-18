package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "create table if not exists users " +
                        "(id integer not null auto_increment primary key, " +
                        "name varchar(100), " +
                        "lastName varchar(100), " +
                        "age integer)";
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "drop table if exists users";
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "insert into users (name, lastName, age) values (?, ?, ?)")) {
                stmt.setString(1, name);
                stmt.setString(2, lastName);
                stmt.setByte(3, age);
                boolean userSaved = stmt.execute();

                if (userSaved) {
                    System.out.println("User с именем — " + name + " добавлен в базу данных");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "delete from users where id=" + id;
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection conn = Util.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                List<User> users = new ArrayList<>();
                String sql = "select * from users";
                stmt.execute(sql);
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setLastName(rs.getString("lastName"));
                    user.setAge(rs.getByte("age"));
                    users.add(user);
                }
                return users;
            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                String sql = "delete from users where id";
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
