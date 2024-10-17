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
        try(Connection conn = Util.getMySQLConnection()){
            Statement stmt = conn.createStatement();
            String sql = "create table if not exists users (id integer not null auto_increment primary key, name varchar(100), lastName varchar(100), age integer)";
            stmt.execute(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection conn = Util.getMySQLConnection()){
            Statement stmt = conn.createStatement();
            String sql = "drop table if exists users";
            stmt.execute(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection conn = Util.getMySQLConnection()){
            Statement stmt = conn.createStatement();
            String sql = "insert into users(name,lastName,age) values(\""+name+"\",\""+lastName+"\",\""+age+"\")";
            stmt.execute(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(Connection conn = Util.getMySQLConnection()){
            Statement stmt = conn.createStatement();
            String sql = "delete from users where id="+id;
            stmt.execute(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try(Connection conn = Util.getMySQLConnection()){
        List<User> users = new ArrayList<User>();
        String sql = "select * from users";
        Statement stmt = conn.createStatement();
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
    } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        public void cleanUsersTable() {
        try(Connection conn = Util.getMySQLConnection()){
            Statement stmt = conn.createStatement();
            String sql = "delete from users where id";
            stmt.execute(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
