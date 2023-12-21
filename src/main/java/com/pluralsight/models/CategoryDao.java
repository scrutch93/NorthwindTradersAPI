package com.pluralsight.models;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {

    public List<Category> getAll() throws SQLException;

    public Category getById(int id);

}
