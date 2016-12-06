package com.henallux.smartcities.DAO;

import com.henallux.smartcities.model.CategoryService;

import java.util.ArrayList;

/**
 * Created by olivierbeguin on 5/12/16.
 */

public interface ICategoryServiceDAO
{
    public ArrayList<CategoryService> getCategoryService(String token) throws Exception;
}
