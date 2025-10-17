package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDaoJDBCImpl = new UserDaoJDBCImpl();
    @Override
    public void createUsersTable() {
        try {
            userDaoJDBCImpl.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void dropUsersTable() {
        try {
            userDaoJDBCImpl.dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            userDaoJDBCImpl.saveUser(name, lastName, age);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void removeUserById(long id) {
        try {
            userDaoJDBCImpl.removeUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<User> getAllUsers() {

        try {
            return userDaoJDBCImpl.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void cleanUsersTable() {
        try {
            userDaoJDBCImpl.cleanUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
