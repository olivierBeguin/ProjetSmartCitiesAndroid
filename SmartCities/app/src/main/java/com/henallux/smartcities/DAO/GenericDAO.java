package com.henallux.smartcities.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by olivierbeguin on 5/12/16.
 */

public class GenericDAO implements IGenericDAO
{

    @Override
    public String getJsonStringWithURL(String token, String urlAdress) throws Exception
    {
        URL url = new URL(urlAdress);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("Authorization", token);
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String stringJSON = "",line;
        while((line = br.readLine()) != null)
        {
            sb.append(line);
        }
        br.close();
        stringJSON = sb.toString();
        return stringJSON;
    }
}
