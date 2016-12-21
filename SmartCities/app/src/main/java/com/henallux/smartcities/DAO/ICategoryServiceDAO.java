package com.henallux.smartcities.DAO;

import com.henallux.smartcities.exception.ConnectionException;
import com.henallux.smartcities.model.CategoryService;
import java.util.ArrayList;


public interface ICategoryServiceDAO
{
    public ArrayList<CategoryService> getCategoryService(String token) throws ConnectionException;
}
