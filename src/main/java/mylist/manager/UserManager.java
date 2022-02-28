package mylist.manager;

import mylist.db.DBConnectionProvider;
import mylist.model.User;

import java.sql.*;

public class UserManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();


    public void registerUser(User user) {

        String sql = "INSERT INTO user (name,surname,email,password,phone) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5,user.getPhone());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserFromResultSet(ResultSet resultSet) {
        try {
            return User.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .password(resultSet.getString(5))
                    .phone(resultSet.getString(6))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByEmailAndPassword(String email, String password) {
        String sql = "select * from user where email = ? and password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int id) {
        String sql = "select * from user where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUserById(int id) {
        String sql = "delete form user where id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public User getUserByEmail(String email) {
        String sql = "select * from user where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,email);
            ResultSet resultSet = ps.executeQuery(sql);
            if (resultSet.next()) {
            return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
