package com.henallux.smartcities.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by olivierbeguin on 5/12/16.
 */

public class UserConnected
{
    private static UserApp userConnected;

    public static UserApp getInstance()
    {
        return userConnected;
    }

    public static void setInstance(UserApp userApp)
    {
        userConnected = userApp;
    }

    /*
    public static UserApp getInstance(UserApp userApp)
    {
        if(userApp != null)
        {
            userConnected = userApp;
        }
        return userConnected;
    }

     */

    public void setUserConnected(Activity activity, UserApp userApp)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Firstname", userApp.getFirstName());
        editor.putString("Lastname", userApp.getLastName());
        editor.putString("Password", userApp.getPassword());
        editor.putString("Email", userApp.getEmail());
        editor.putString("PhoneNumber", userApp.getPhoneNumber());
        editor.putString("Street", userApp.getStreet());
        editor.putString("City", userApp.getCity());
        editor.putString("Country", userApp.getCountry());
        editor.putString("Category", userApp.getCategory());
        editor.putString("DateInscription", sdf.format(userApp.getDateInscription()));
        editor.putString("PostalCode", userApp.getPostalCode());
        editor.putString("Number", userApp.getNumber());
        editor.putInt("SumServiceDone", userApp.getSumServiceDone());
        editor.putInt("SumServiceGiven", userApp.getSumServiceGiven());
    }

    public UserApp getUserConnected(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        try {
            userConnected = new UserApp(sharedPreferences.getString("Id", ""), sharedPreferences.getString("Firstname", ""), sharedPreferences.getString("Lastname", ""), sharedPreferences.getString("Password", ""), sharedPreferences.getString("PhoneNumber", ""), sharedPreferences.getString("Street", ""), sharedPreferences.getString("City", ""), sharedPreferences.getString("Country", ""), sharedPreferences.getString("Category", ""), sdf.parse(sharedPreferences.getString("DateInscription", "")), sharedPreferences.getString("PostalCode", ""), sharedPreferences.getString("Number", ""), sharedPreferences.getInt("SumServiceDone", 0), sharedPreferences.getInt("SumServiceGiven", 0));
        }
        catch (ParseException e)
        {

        }
        return userConnected;
    }
}
