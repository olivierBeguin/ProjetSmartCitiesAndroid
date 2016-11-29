package com.henallux.smartcities.DAO;

import android.util.Log;

import com.henallux.smartcities.model.UserApp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Created by olivierbeguin on 22/11/16.
 */

public class UserAppDAO implements IUserAppDAO
{

    @Override
    public String getUserWithMailandPw(String email, String pw) throws Exception
    {
        URL url = new URL("http://g-aideappweb.azurewebsites.net/token");
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-type", "x-www-form-urlencoded");
        urlConnection.setDoInput(true);


        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();


        writer.write("Username="+email+"&Password="+pw+"&grant_type=password");
        writer.flush();
        BufferedReader br;
        if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() <= 299)
        {
            br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
        } else
        {
            br = new BufferedReader(new InputStreamReader((urlConnection.getErrorStream())));
        }

        StringBuilder sb = new StringBuilder();
        String stringJSON = "",line;
        while((line = br.readLine()) != null)
        {
            sb.append(line);
        }
        br.close();
        stringJSON = sb.toString();

        writer.close();
        out.close();

        urlConnection.disconnect();
        return jsonToToken(stringJSON);
    }

     public UserApp getUser() throws Exception
     {
        URL url = new URL("http://g-aideappweb.azurewebsites.net/api/userapps/1");
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
        return jsonToUserApp(stringJSON);
    }

    private UserApp jsonToUserApp(String stringJSON) throws Exception
    {
        UserApp userApp;
        JSONObject jsonUserApp = new JSONObject(stringJSON);
        userApp = new UserApp(jsonUserApp.getString("FirstName"), jsonUserApp.getString("LastName"), jsonUserApp.getString("Password"), jsonUserApp.getString("AdressMail"), jsonUserApp.getString("PhoneNumber"), jsonUserApp.getString("Street"), jsonUserApp.getString("City"), jsonUserApp.getString("Country"), new Date(), jsonUserApp.getString("PostalCode"), jsonUserApp.getString("Number"), jsonUserApp.getInt("NumGetService"), jsonUserApp.getInt("NumServiceGive"));
        return userApp;
    }

    private String jsonToToken(String stringJSON) throws Exception
    {
        String test = new JSONObject(stringJSON).getString("access_token");
        return test;
    }


    @Override
    public void setUser(UserApp userApp)
    {

    }

    @Override
    public void updateUser(UserApp userApp)
    {

    }
}
