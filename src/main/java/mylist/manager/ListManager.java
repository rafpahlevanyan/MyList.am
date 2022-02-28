package mylist.manager;

import mylist.db.DBConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserManager userManager = new UserManager();
    private CategoryManager categoryManager = new CategoryManager();

    public void addItem(mylist.model.List list) {
        String sql = "insert into list(title,price,description,pic_url,user_id,category_id) values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, list.getTitle());
            ps.setDouble(2, list.getPrice());
            ps.setString(3, list.getDescription());
            ps.setString(4, list.getPicUrl());
            ps.setInt(5, list.getUser().getId());
            ps.setInt(6, list.getCategory().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                list.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<mylist.model.List> getLast20Items() {
        List<mylist.model.List> items = new ArrayList<>();
        String sql = "SELECT * FROM list ORDER BY id DESC limit 20";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<mylist.model.List> getLast20ItemsByCategory(int categoryId) {
        List<mylist.model.List> items = new ArrayList<>();
        String sql = "SELECT * FROM list where category_id = ? ORDER BY id DESC limit 20";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    private mylist.model.List getItemFromResultSet(ResultSet resultSet) {
        try {
            return mylist.model.List
                    .builder()
                    .id(resultSet.getInt(1))
                    .title(resultSet.getString(2))
                    .price(resultSet.getInt(3))
                    .description(resultSet.getString(4))
                    .picUrl(resultSet.getString(5))
                    .user(userManager.getUserById(resultSet.getInt(6)))
                    .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<mylist.model.List> getAllItemsByUser(int userId) {
        List<mylist.model.List> items = new ArrayList<>();
        String sql = "SELECT * FROM list WHERE user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(getItemFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public boolean deleteItem(int id) {
        String sql = "DELETE FREE list WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public mylist.model.List getItemById(int id) {
        String sql = "select * from list where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<mylist.model.List> getByCategoryId(int id) {
        List<mylist.model.List> items = new ArrayList<>();
        String sql = "SELECT * FROM list WHERE category_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(getItemFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

}
