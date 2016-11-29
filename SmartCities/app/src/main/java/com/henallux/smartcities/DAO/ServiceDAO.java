package com.henallux.smartcities.DAO;

import android.util.Log;

import com.henallux.smartcities.model.CategoryService;
import com.henallux.smartcities.model.Service;
import com.henallux.smartcities.model.UserApp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.util.Date;
import java.util.List;

/**
 * Created by olivierbeguin on 22/11/16.
 */

public class ServiceDAO implements IServiceDAO
{
    @Override
    public Service getServicesWithUser(UserApp userApp) throws Exception
    {
        URL url = new URL("http://g-aideappweb.azurewebsites.net/api/services/1");
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
        //Log.i("Test", stringJSON);
        return jsonToService(stringJSON);
    }

    private Service jsonToService(String stringJSON) throws Exception
    {
        Service service;
        JSONObject jsonService = new JSONObject(stringJSON);
        service = new Service(jsonService.getString("DescriptionService"), new Date());
        return service;
    }

    @Override
    public List<Service> getServicesWithCategory(CategoryService categoryService, String researchString) {
        return null;
    }

    @Override
    public void createService(Service service) {

    }
}
