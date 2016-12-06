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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by olivierbeguin on 22/11/16.
 */

public class ServiceDAO extends GenericDAO implements IServiceDAO
{
    @Override
    public Service getServicesWithUser(UserApp userApp) throws Exception
    {
        /*URL url = new URL("http://g-aideappweb.azurewebsites.net/api/services/1");
        URLConnection urlConnection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String stringJSON = "",line;
        while((line = br.readLine()) != null)
        {
            sb.append(line);
        }
        br.close();
        stringJSON = sb.toString();
        //Log.i("Test", stringJSON);*/
        return null;//jsonToService(stringJSON);
    }

    private ArrayList<Service> jsonToServices(String stringJSON) throws Exception
    {
        ArrayList<Service> services = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(stringJSON);
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonService = new JSONObject(stringJSON);
            services.add(new Service(jsonService.getString("DescriptionService"), new Date()));
        }
        return services;
    }

    @Override
    public ArrayList<Service> getServices(String token) throws Exception
    {
        String stringJSON = getJsonStringWithURL(token, "http://g-aideappweb.azurewebsites.net/api/services");
        return jsonToServices(stringJSON);
    }

    @Override
    public void createService(Service service) {

    }
}
