package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    // Таблица newschema в схеме ildaf
    // (безопасно экранировано бэктиками на случай «неудачных» имён)
    private static final String TABLE = "`ildaf`.`newschema`";

    private final Connection connection = getConnection();

    public UserDaoJDBCImpl() { }

    @Override
    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
                + "id BIGINT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(255), "
                + "lastName VARCHAR(255), "
                + "age TINYINT"
                + ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + TABLE;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO " + TABLE + " (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        }
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM " + TABLE + " WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT id, name, lastName, age FROM " + TABLE;
        List<User> users = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getLong("id"));
                u.setName(rs.getString("name"));
                u.setLastName(rs.getString("lastName"));
                u.setAge(rs.getByte("age"));
                users.add(u);
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        // Если вдруг нет прав на TRUNCATE — можно заменить на: "DELETE FROM " + TABLE
        String sql = "TRUNCATE TABLE " + TABLE;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}