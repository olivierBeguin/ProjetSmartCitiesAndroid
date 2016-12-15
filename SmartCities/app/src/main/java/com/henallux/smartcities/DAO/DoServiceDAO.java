package com.henallux.smartcities.DAO;

import com.henallux.smartcities.model.DoService;
import com.henallux.smartcities.model.Service;
import com.henallux.smartcities.model.UserApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by oli07 on 10-12-16.
 */

public class DoServiceDAO extends GenericDAO implements IDoServiceDAO
{
    @Override
    public ArrayList<DoService> getDoServiceOfUser(String token, String username) throws Exception
    {
        String stringJSON = getJsonStringWithURL(token, "http://g-aideappweb.azurewebsites.net/api/doServicesUser/?username="+username);
        return jsonToDoServices(stringJSON);
    }

    @Override
    public void postDoService(String token, DoService doService) throws Exception
    {
        String jsonString = doServiceToJson(doService);
        postJsonStringWithURL(token, jsonString, "http://g-aideappweb.azurewebsites.net/api/doServices");
    }

    @Override
    public void putDoService(String token, DoService doService) throws Exception
    {
        String jsonString = doServiceCommentToJson(doService);
        putJsonStringWithURL(token, jsonString, "http://g-aideappweb.azurewebsites.net/api/doServices/?id="+doService.getId());
    }

    private ArrayList<DoService> jsonToDoServices(String stringJSON) throws Exception
    {
        ArrayList<DoService> doServices = new ArrayList<>();
        JSONArray jsonArrayDoServices = new JSONArray(stringJSON);
        for (int i = 0; i < jsonArrayDoServices.length(); i++)
        {
            JSONObject jsonDoService = jsonArrayDoServices.getJSONObject(i);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            JSONObject jsonUserDoService = jsonDoService.getJSONObject("UserDoService");
            UserApp userApp = new UserApp(jsonUserDoService.getString("Id"), jsonUserDoService.getString("FirstName"), jsonUserDoService.getString("LastName"), jsonUserDoService.getString("Email"), jsonUserDoService.getString("PhoneNumber"), jsonUserDoService.getString("Street"), jsonUserDoService.getString("City"), jsonUserDoService.getString("Country"), jsonUserDoService.getString("Category"), new Date(), jsonUserDoService.getString("PostalCode"), jsonUserDoService.getString("Number"), jsonUserDoService.getInt("NumGetService"), jsonUserDoService.getInt("NumServiceGive"));
            JSONObject jsonServiceDone = jsonDoService.getJSONObject("ServiceDone");
            Service service = new Service(jsonServiceDone.getInt("Id"), jsonServiceDone.getString("Label"), jsonServiceDone.getString("DescriptionService"), sdf.parse(jsonServiceDone.getString("DatePublicationService")));
            String comment;
            if(jsonDoService.getString("CommentDescription").equals("null"))
                comment = null;
            else
                comment = jsonDoService.getString("CommentDescription");
            doServices.add(new DoService(jsonDoService.getInt("Id"), sdf.parse(jsonDoService.getString("DateService")), userApp, service, comment, jsonDoService.getDouble("Rating")));
        }
        return doServices;
    }

    private String doServiceToJson(DoService doService) throws Exception
    {
        JSONObject jsonDoService = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        jsonDoService.accumulate("DateService", sdf.format(doService.getDateService()));
        jsonDoService.accumulate("UserDoService", doService.getUserDoService().getEmail());
        jsonDoService.accumulate("ServiceDone", doService.getServiceDone().getId());
        return jsonDoService.toString();
    }

    private String doServiceCommentToJson(DoService doService) throws Exception
    {
        JSONObject jsonDoService = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        jsonDoService.accumulate("DateService", sdf.format(doService.getDateService()));
        jsonDoService.accumulate("UserDoService", doService.getUserDoService().getEmail());
        jsonDoService.accumulate("ServiceDone", doService.getServiceDone().getId());
        jsonDoService.accumulate("CommentDescription", doService.getComment());
        jsonDoService.accumulate("Rating", doService.getRating());
        return jsonDoService.toString();
    }

    @Override
    public ArrayList<DoService> getDoServicesReceived(String token, String email) throws Exception {
        String stringJSON = getJsonStringWithURL(token, "http://g-aideappweb.azurewebsites.net/api/doServicesReceivedUser/?email="+email);
        return jsonToDoServices(stringJSON);
    }
}
