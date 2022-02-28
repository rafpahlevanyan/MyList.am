package mylist.manager;

import mylist.db.DBConnectionProvider;
import mylist.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();


    public Category getCategoryById(int id) {
        String sql = "select * from category where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = Category.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .build();
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;

    }

}
