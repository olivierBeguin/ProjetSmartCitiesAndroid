package com.henallux.smartcities.DAO;

import com.henallux.smartcities.model.DoService;
import com.henallux.smartcities.model.Service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by oli07 on 10-12-16.
 */

public class DoServiceDAO extends GenericDAO implements IDoServiceDAO
{
    @Override
    public ArrayList<DoService> getDoServiceOfUser(String token, String username) throws Exception
    {
        String stringJSON = getJsonStringWithURL(token, "http://g-aideappweb.azurewebsites.net/api/users/?username="+username);
        return jsonToDoServices(stringJSON);
    }

    private ArrayList<DoService> jsonToDoServices(String stringJSON) throws Exception
    {
        ArrayList<DoService> doServices = new ArrayList<>();
        JSONArray jsonArrayDoServices = new JSONArray(stringJSON);
        for (int i = 0; i < jsonArrayDoServices.length(); i++)
        {
            JSONObject jsonDoService = jsonArrayDoServices.getJSONObject(i);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            //doServices.add(new DoService(sdf.parse(jsonDoService.getString("DateService")), );
        }
        return null;
    }

    private ArrayList<Service> jsonToServices(String stringJSON) throws Exception
    {
        ArrayList<Service> services = new ArrayList<>();
        JSONArray jsonArrayService = new JSONArray(stringJSON);
        for (int i = 0; i < jsonArrayService.length(); i++)
        {
            JSONObject jsonService = jsonArrayService.getJSONObject(i);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            services.add(new Service(jsonService.getString("Label"), jsonService.getString("DescriptionService"), sdf.parse(jsonService.getString("DatePublicationService"))));
            JSONObject jsonCategory = jsonService.getJSONObject("Category");
            //services.get(i).setCategory(new CategoryService(jsonCategory.getString("Label")));
        }
        return services;
    }
}
