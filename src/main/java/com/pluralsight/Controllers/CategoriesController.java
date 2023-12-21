package com.pluralsight.Controllers;

import com.pluralsight.models.Category;
import com.pluralsight.models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class CategoriesController {

    List<Category> categoryList =new ArrayList<>();


    @RequestMapping(path="/categories", method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue="section") String category){


        categoryList.add(new Category(1,"dairy"));
        categoryList.add(new Category(2, "grains"));
        categoryList.add(new Category(3, "snacks"));




        return categoryList.toString();
    }


}



