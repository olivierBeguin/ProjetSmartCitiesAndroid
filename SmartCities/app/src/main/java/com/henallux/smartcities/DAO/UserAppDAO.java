package com.henallux.smartcities.DAO;

import android.util.Log;

import com.google.gson.Gson;
import com.henallux.smartcities.exception.ConnectionException;
import com.henallux.smartcities.model.UserApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.zip.InflaterOutputStream;

/**
 * Created by olivierbeguin on 22/11/16.
 */

public class UserAppDAO extends GenericDAO implements IUserAppDAO
{

    @Override
    public String getUserWithMailandPw(String email, String pw) throws ConnectionException
    {
        try {
            URL url = new URL("http://g-aideappweb.azurewebsites.net/token");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-type", "x-www-form-urlencoded");
            urlConnection.setDoInput(true);

            OutputStream out = urlConnection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out);
            urlConnection.connect();


            writer.write("Username=" + email + "&Password=" + pw + "&grant_type=password");
            writer.flush();
            BufferedReader br;
            if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() <= 299) {
                br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
            } else {
                br = new BufferedReader(new InputStreamReader((urlConnection.getErrorStream())));
            }

            StringBuilder sb = new StringBuilder();
            String stringJSON = "", line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            stringJSON = sb.toString();

            writer.close();
            out.close();

            urlConnection.disconnect();
            return jsonToToken(stringJSON);
        }
        catch (IOException e)
        {
            throw new ConnectionException(false);
        }
        catch (Exception e)
        {
            throw new ConnectionException(true);
        }
    }

    public UserApp getUser(String token, String email) throws Exception
    {
        String stringJSON = getJsonStringWithURL(token, "http://g-aideappweb.azurewebsites.net/api/users/?username="+email);
        return jsonToUserApp(stringJSON);
    }

    private UserApp jsonToUserApp(String stringJSON) throws Exception
    {
        UserApp userApp = null;
        JSONObject jsonUserApp = new JSONObject(stringJSON);
        userApp = new UserApp(jsonUserApp.getString("Id"), jsonUserApp.getString("FirstName"), jsonUserApp.getString("LastName"), jsonUserApp.getString("Email"), jsonUserApp.getString("PhoneNumber"), jsonUserApp.getString("Street"), jsonUserApp.getString("City"), jsonUserApp.getString("Country"), jsonUserApp.getString("Category"), new Date(), jsonUserApp.getString("PostalCode"), jsonUserApp.getString("Number"), jsonUserApp.getInt("NumGetService"), jsonUserApp.getInt("NumServiceGive"));
        return userApp;
    }

    private String jsonToToken(String stringJSON) throws Exception
    {
        try
        {
            String token = new JSONObject(stringJSON).getString("access_token");
            return token;
        }
        catch (JSONException e)
        {
            throw new ConnectionException(true);
        }

    }


    @Override
    public void registerUser(UserApp userApp) throws Exception
    {
        URL url = new URL("http://g-aideappweb.azurewebsites.net/api/Account/Register");
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-type", "application/json");
        urlConnection.setDoOutput(true);

        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();

        JSONObject jsonUser = new JSONObject();
        jsonUser.accumulate("Password", userApp.getPassword());
        jsonUser.accumulate("ConfirmPassword", userApp.getPassword());
        jsonUser.accumulate("Email", userApp.getEmail());
        jsonUser.accumulate("FirstName", userApp.getFirstName());
        jsonUser.accumulate("LastName", userApp.getLastName());
        jsonUser.accumulate("Street", userApp.getStreet());
        jsonUser.accumulate("Number", userApp.getNumber());
        jsonUser.accumulate("PostalCode", userApp.getPostalCode());
        jsonUser.accumulate("City", userApp.getCity());
        jsonUser.accumulate("Country", userApp.getCountry());
        jsonUser.accumulate("Category", userApp.getCategory());
        jsonUser.accumulate("PhoneNumber", userApp.getPhoneNumber());

        String jsonString = jsonUser.toString();

        writer.write(jsonString);
        writer.flush();

        if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() <= 299)
        {
            Log.i("Test", "Crée");
        }
        else
        {
            Log.i("Test", urlConnection.getResponseMessage());

        }

        writer.close();
        out.close();
        urlConnection.disconnect();
    }

    @Override
    public void updateUser(UserApp userApp)
    {

    }
}
