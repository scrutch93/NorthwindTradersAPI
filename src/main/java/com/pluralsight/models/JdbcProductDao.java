package com.pluralsight.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    private DataSource dataSource;

    @Autowired
    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        //Arraylists are good for returning multiple items
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products; ";

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rows = statement.executeQuery(sql);
            while (rows.next()) {
                Product product = new Product();
                product.setProductId(rows.getInt("ProductID"));
                product.setProductName(rows.getString("ProductName"));
                product.setCategoryId(rows.getInt("CategoryID"));
                product.setUnitPrice(rows.getFloat("UnitPrice"));

                // Add the current product to the list
                products.add(product);
            }
        }

        return products; // Return the list of products
    }

    @Override
    public Product getById(int id) {
        String sql = "SELECT * FROM products WHERE ProductID = ?";
        //doing this because we are retrieving a single product
        Product product = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product();
                    product.setProductId(resultSet.getInt("ProductID"));
                    product.setProductName(resultSet.getString("ProductName"));
                    product.setCategoryId(resultSet.getInt("CategoryID"));
                    product.setUnitPrice(resultSet.getFloat("UnitPrice"));
                }
            }

        } catch (SQLException e) {
            // Handle any potential exceptions (log or throw as needed)
            e.printStackTrace();
        }

        return product;
    }
}