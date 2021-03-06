package com.henallux.smartcities.DAO;


import com.henallux.smartcities.exception.ConnectionException;
import com.henallux.smartcities.model.CategoryService;
import com.henallux.smartcities.model.Service;
import com.henallux.smartcities.model.UserApp;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ServiceDAO extends GenericDAO implements IServiceDAO
{
    private ArrayList<Service> jsonToServices(String stringJSON) throws ConnectionException
    {
        try {
            ArrayList<Service> services = new ArrayList<>();
            JSONArray jsonArrayService = new JSONArray(stringJSON);
            for (int i = 0; i < jsonArrayService.length(); i++) {
                JSONObject jsonService = jsonArrayService.getJSONObject(i);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                services.add(new Service(jsonService.getInt("Id"), jsonService.getString("Label"), jsonService.getString("DescriptionService"), sdf.parse(jsonService.getString("DatePublicationService"))));
                JSONObject jsonCategory = jsonService.getJSONObject("Category");
                services.get(i).setCategory(new CategoryService(jsonCategory.getInt("Id"), jsonCategory.getString("Label")));
                JSONObject jsonUserApp = jsonService.getJSONObject("UserNeedService");
                services.get(i).setUserNeedService(new UserApp(jsonUserApp.getString("Id"), jsonUserApp.getString("FirstName"), jsonUserApp.getString("LastName"), jsonUserApp.getString("Email"), jsonUserApp.getString("PhoneNumber"), jsonUserApp.getString("Street"), jsonUserApp.getString("City"), jsonUserApp.getString("Country"), jsonUserApp.getString("Category"), new Date(), jsonUserApp.getString("PostalCode"), jsonUserApp.getString("Number"), jsonUserApp.getInt("NumGetService"), jsonUserApp.getInt("NumServiceGive")));
            }
            return services;
        }
        catch (Exception e)
        {
            throw new ConnectionException(false);
        }
    }

    private String serviceToJson(Service service) throws ConnectionException
    {
        try {
            JSONObject jsonService = new JSONObject();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            jsonService.accumulate("Label", service.getLabelService());
            jsonService.accumulate("DescriptionService", service.getDescriptionService());
            jsonService.accumulate("DatePublicationService", sdf.format(service.getDatePublicationService()));
            jsonService.accumulate("ServiceDone", service.getServiceDone());
            jsonService.accumulate("UserNeedService", service.getUserNeedService().getEmail());
            jsonService.accumulate("Category", service.getCategory().getId());
            return jsonService.toString();
        }
        catch (Exception e)
        {
            throw new ConnectionException(false);
        }
    }

    @Override
    public ArrayList<Service> getServices(String token, String email) throws ConnectionException
    {
        String stringJSON = getJsonStringWithURL(token, "http://g-aideappweb.azurewebsites.net/api/servicesUser/?userName="+email);
        return jsonToServices(stringJSON);
    }

    @Override
    public void postServices(String token, Service service) throws ConnectionException
    {
        String stringJSON = serviceToJson(service);
        postJsonStringWithURL(token, stringJSON, "http://g-aideappweb.azurewebsites.net/api/services");
    }

    @Override
    public void putService(String token, Service service) throws ConnectionException
    {
        String stringJson = serviceToJson(service);
        putJsonStringWithURL(token, stringJson, "http://g-aideappweb.azurewebsites.net/api/services/"+service.getId());
    }


}
