package com.henallux.smartcities.model;

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
}
