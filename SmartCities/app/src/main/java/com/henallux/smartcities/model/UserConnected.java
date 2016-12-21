package com.henallux.smartcities.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.henallux.smartcities.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class UserConnected
{
    private UserApp userConnected;

    public void resetUserConnected(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Firstname", "");
        editor.putString("Lastname", "");
        editor.putString("Password", "");
        editor.putString("Email", "");
        editor.putString("PhoneNumber", "");
        editor.putString("Street", "");
        editor.putString("City", "");
        editor.putString("Country", "");
        editor.putString("Category", "");
        editor.putString("DateInscription", "");
        editor.putString("PostalCode", "");
        editor.putString("Number", "");
        editor.putInt("SumServiceDone", 0);
        editor.putInt("SumServiceGiven", 0);
        editor.commit();
    }

    public void setUserConnected(Activity activity, UserApp userApp)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
        editor.commit();
    }

    public UserApp getUserConnected(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            userConnected = new UserApp(sharedPreferences.getString("Id", ""), sharedPreferences.getString("Firstname", ""), sharedPreferences.getString("Lastname", ""), sharedPreferences.getString("Email", ""), sharedPreferences.getString("PhoneNumber", ""), sharedPreferences.getString("Street", ""), sharedPreferences.getString("City", ""), sharedPreferences.getString("Country", ""), sharedPreferences.getString("Category", ""), sdf.parse(sharedPreferences.getString("DateInscription", "")), sharedPreferences.getString("PostalCode", ""), sharedPreferences.getString("Number", ""), sharedPreferences.getInt("SumServiceDone", 0), sharedPreferences.getInt("SumServiceGiven", 0));
        }
        catch (ParseException e)
        {
            Log.i("Test", e.getMessage());
        }
        return userConnected;
    }

    public void resetToken(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(String.valueOf(R.string.myPref), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token", "");
        editor.commit();
    }

    public String getToken(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(String.valueOf(R.string.myPref), Context.MODE_PRIVATE);
        return sharedPreferences.getString("Token", "");
    }

    public void setToken(Activity activity, String token)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(String.valueOf(R.string.myPref), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token", token);
        editor.commit();
    }
}
