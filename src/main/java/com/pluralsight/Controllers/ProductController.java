package com.pluralsight.Controllers;

import com.pluralsight.models.Category;
import com.pluralsight.models.Product;
import com.pluralsight.models.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductDao dao;

    @Autowired
    public ProductController(ProductDao dao)
    {
        this.dao = dao;
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getProducts() throws SQLException {

      List<Product> products = dao.getAll();

        return products;
    }

    @RequestMapping(path="/products/{id}",  method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id)
    {
      Product product = dao.getById(id);

      return product;
    }

}