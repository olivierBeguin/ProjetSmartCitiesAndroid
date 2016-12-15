package com.henallux.smartcities.service;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.henallux.smartcities.R;
import com.henallux.smartcities.exception.FormException;
import com.henallux.smartcities.exception.RechercheServiceException;
import com.henallux.smartcities.model.CategoryService;
import com.henallux.smartcities.model.DoService;
import com.henallux.smartcities.model.DoServicesAdapter;
import com.henallux.smartcities.model.Service;
import com.henallux.smartcities.model.ServicesAdapter;
import com.henallux.smartcities.model.ServicesReceivedAdapter;
import com.henallux.smartcities.model.UserApp;

import java.util.ArrayList;


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

    public static ArrayList<Service> triServiceCat(ArrayList<Service> services, String categoryService)
    {
        ArrayList<Service> servicesCat = new ArrayList<>();
        for (Service service : services)
        {
            if(service.getCategory().getLabel().equals(categoryService) || categoryService.equals("Selectionnez une catégorie"))
                servicesCat.add(service);
        }
        return servicesCat;
    }

    public static Service rechercheService(ListView listView, ArrayList<Service> services) throws RechercheServiceException
    {
        int pos = ((ServicesAdapter)listView.getAdapter()).getPositionChecked();
        String label;
        label = String.valueOf(((TextView) listView.getChildAt(pos).findViewById(R.id.label_list)).getText());


        for (Service service : services)
        {
            if(service.getLabelService().equals(label))
                return service;
        }
        throw new RechercheServiceException("Contacter Louis");
    }

    public static DoService rechercheDoService(ListView listView, ArrayList<DoService> doServices) throws RechercheServiceException
    {
        int pos = ((ServicesReceivedAdapter)listView.getAdapter()).getPositionChecked();
        String label;
        label = String.valueOf(((TextView) listView.getChildAt(pos).findViewById(R.id.label_list)).getText());

        for (DoService doService : doServices)
        {
            if(doService.getServiceDone().getLabelService().equals(label))
                return doService;
        }
        throw new RechercheServiceException("Contacter Louis");
    }

    public static Boolean compareTwoUserAndAddDifference(UserApp userApp, UserApp userModif)
    {
        Boolean change = false;
        if(!userApp.getFirstName().equals(userModif.getFirstName())) {
            change = true;
            userApp.setFirstName(userModif.getFirstName());
        }
        if(!userApp.getLastName().equals(userModif.getLastName())) {
            change = true;
            userApp.setLastName(userModif.getLastName());
        }
        if(!userApp.getEmail().equals(userModif.getEmail())) {
            change = true;
            userApp.setEmail(userModif.getEmail());
        }
        if(!userApp.getPhoneNumber().equals(userModif.getPhoneNumber())) {
            change = true;
            userApp.setPhoneNumber(userModif.getPhoneNumber());
        }
        if(!userApp.getStreet().equals(userModif.getStreet())) {
            change = true;
            userApp.setStreet(userModif.getStreet());
        }
        if(!userApp.getNumber().equals(userModif.getNumber())) {
            change = true;
            userApp.setNumber(userModif.getNumber());
        }
        if(!userApp.getPostalCode().equals(userModif.getPostalCode())) {
            change = true;
            userApp.setPostalCode(userModif.getPostalCode());
        }
        if(!userApp.getCity().equals(userModif.getCity())) {
            change = true;
            userApp.setCity(userModif.getCity());
        }
        if(!userApp.getCountry().equals(userModif.getCountry())) {
            change = true;
            userApp.setCountry(userModif.getCountry());
        }
        return change;
    }
}
