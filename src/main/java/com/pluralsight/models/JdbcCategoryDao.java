package com.pluralsight.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCategoryDao implements CategoryDao {

    private DataSource dataSource;

    @Autowired
    public JdbcCategoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> getAll() throws SQLException {
        //Arraylists are good for returning multiple items
        List<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM categories; ";

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rows = statement.executeQuery(sql);
            while (rows.next()) {
                Category category = new Category();
                category.setCategoryName(rows.getString("CategoryName"));
                category.setCategoryId(rows.getInt("CategoryID"));
                // Add the current product to the list
                categories.add(category);
            }
        }

        return categories; // Return the list of products
    }

    @Override
    public Category getById(int id) {
        String sql = "SELECT * FROM categories WHERE CategoryID = ?";
        //doing this because we are retrieving a single product
        Category category = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    category = new Category();
                    category.setCategoryId(resultSet.getInt("CategoryID"));
                    category.setCategoryName(resultSet.getString("CategoryName"));

                }
            }

        } catch (SQLException e) {
            // Handle any potential exceptions (log or throw as needed)
            e.printStackTrace();
        }

        return category;
    }
}