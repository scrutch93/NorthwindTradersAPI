package com.pluralsight.models;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    public List<Product> getAll() throws SQLException;
    public Product getById(int id);



}
