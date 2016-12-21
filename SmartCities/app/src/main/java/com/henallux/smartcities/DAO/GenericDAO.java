package com.henallux.smartcities.DAO;

import android.util.Log;

import com.henallux.smartcities.exception.ConnectionException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class GenericDAO implements IGenericDAO
{

    @Override
    public String getJsonStringWithURL(String token, String urlAdress) throws ConnectionException
    {
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("Authorization", "Bearer " + token);
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String stringJSON, line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            stringJSON = sb.toString();
            return stringJSON;
        }
        catch (Exception e)
        {
            throw new ConnectionException(false);
        }
    }

    public void putJsonStringWithURL(String token, String jsonString, String urlAdress) throws ConnectionException
    {
        try
        {
            URL url = new URL(urlAdress);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("PUT");
            urlConnection.setRequestProperty("Content-type", "application/json");
            urlConnection.setRequestProperty("Authorization", "Bearer " + token);
            urlConnection.setDoOutput(true);

            OutputStream out = urlConnection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out);
            urlConnection.connect();


            writer.write(jsonString);
            writer.flush();

            if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() <= 299) {
                Log.i("Test", "Url connection bonne");
            }
            else
            {

            }

            writer.close();
            out.close();
            urlConnection.disconnect();
        }
        catch (Exception e)
        {
            throw new ConnectionException(false);
        }
    }

    public void postJsonStringWithURL(String token, String jsonString, String urlAdress) throws ConnectionException
    {
        try {
            URL url = new URL(urlAdress);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-type", "application/json");
            urlConnection.setRequestProperty("Authorization", "Bearer " + token);
            urlConnection.setDoOutput(true);

            OutputStream out = urlConnection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out);
            urlConnection.connect();


            writer.write(jsonString);
            writer.flush();

            if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() <= 299) {
                Log.i("Test", "Url connection bonne");
            } else {
                throw new ConnectionException(false);
            }

            writer.close();
            out.close();
            urlConnection.disconnect();
        }
        catch (Exception e)
        {
            throw new ConnectionException(false);
        }
    }
}
