package com.henallux.smartcities.DAO;

import com.henallux.smartcities.exception.ConnectionException;
import com.henallux.smartcities.model.CategoryService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryServiceDAO extends GenericDAO implements ICategoryServiceDAO
{

    @Override
    public ArrayList<CategoryService> getCategoryService(String token) throws ConnectionException
    {
        String stringJSON = getJsonStringWithURL(token, "http://g-aideappweb.azurewebsites.net/api/categoryServices");
        return jsonToCategoryServices(stringJSON);
    }

    private ArrayList<CategoryService> jsonToCategoryServices(String stringJSON) throws ConnectionException
    {
        try {
            ArrayList<CategoryService> categoryServices = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(stringJSON);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonCategoryService = jsonArray.getJSONObject(i);
                categoryServices.add(new CategoryService(jsonCategoryService.getInt("Id"), jsonCategoryService.getString("Label")));
            }
            return categoryServices;
        }
        catch (Exception e)
        {
            throw new ConnectionException(false);
        }
    }
}
