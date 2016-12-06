package com.henallux.smartcities.DAO;

import com.henallux.smartcities.model.CategoryService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by olivierbeguin on 5/12/16.
 */

public class CategoryServiceDAO extends GenericDAO implements ICategoryServiceDAO
{

    @Override
    public ArrayList<CategoryService> getCategoryService(String token) throws Exception
    {
        String stringJSON = getJsonStringWithURL(token, "http://g-aideappweb.azurewebsites.net/api/categoryService");
        return jsonToCategoryServices(stringJSON);
    }

    private ArrayList<CategoryService> jsonToCategoryServices(String stringJSON) throws Exception
    {
        ArrayList<CategoryService> categoryServices = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(stringJSON);
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonCategoryService = new JSONObject(stringJSON);
            categoryServices.add(new CategoryService(jsonCategoryService.getString("DescriptionService")));
        }
        return categoryServices;
    }
}
