package com.henallux.smartcities.DAO;

import android.util.Log;

import com.henallux.smartcities.model.CategoryService;
import com.henallux.smartcities.model.Service;
import com.henallux.smartcities.model.UserApp;
import com.henallux.smartcities.DAO.GenericDAO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by olivierbeguin on 22/11/16.
 */

public class ServiceDAO extends GenericDAO implements IServiceDAO
{
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
            services.get(i).setCategory(new CategoryService(jsonCategory.getInt("Id"), jsonCategory.getString("Label")));
        }
        return services;
    }

    private String serviceToJson(Service service) throws Exception
    {
        JSONObject jsonService = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        jsonService.accumulate("Label", service.getLabelService());
        jsonService.accumulate("DescriptionService", service.getDescriptionService());
        jsonService.accumulate("DatePublicationService", sdf.format(service.getDatePublicationService()));
        jsonService.accumulate("ServiceDone", service.getServiceDone());
        jsonService.accumulate("UserNeedService", service.getUserNeedService().getEmail());
        jsonService.accumulate("Category", service.getCategory().getId());
        return jsonService.toString();
    }

    @Override
    public ArrayList<Service> getServices(String token) throws Exception
    {
        String stringJSON = getJsonStringWithURL(token, "http://g-aideappweb.azurewebsites.net/api/services");
        return jsonToServices(stringJSON);
    }

    @Override
    public void postServices(String token, Service service) throws Exception
    {
        String stringJSON = serviceToJson(service);
        postJsonStringWithURL(token, stringJSON, "http://g-aideappweb.azurewebsites.net/api/services/?userName="+service.getUserNeedService().getEmail()+"&idCat="+service.getCategory().getId());
    }
}
