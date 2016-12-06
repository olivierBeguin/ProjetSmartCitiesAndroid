package com.henallux.smartcities.service;

import android.app.Activity;

import com.henallux.smartcities.R;
import com.henallux.smartcities.exception.FormException;
import com.henallux.smartcities.model.UserApp;


/**
 * Created by olivierbeguin on 6/12/16.
 */

public class Business
{
    public static void verifyEntry(UserApp userApp, String confirmPassword, Activity context) throws FormException
    {
        if (userApp.getLastName().isEmpty() || userApp.getFirstName().isEmpty() || userApp.getPassword().isEmpty() || confirmPassword.isEmpty() || userApp.getEmail().isEmpty() || userApp.getPhoneNumber().isEmpty() ||userApp.getStreet().isEmpty() || userApp.getCity().isEmpty() || userApp.getCountry().isEmpty() || userApp.getPostalCode().isEmpty() || userApp.getNumber().isEmpty())
        {
            throw new FormException(context.getString(R.string.not_filled));
        }

        if (!confirmPassword.equals(userApp.getPassword()))
        {
            throw new FormException(context.getString(R.string.dont_match));
        }

        if (!userApp.getEmail().matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
        {
            throw new FormException(context.getString(R.string.error_adressMail));
        }
        if(userApp.getCategory().equals("Jeune"))
        {
            userApp.setCategory("young");
        }
        else
        {
            userApp.setCategory("old");
        }
    }
}
