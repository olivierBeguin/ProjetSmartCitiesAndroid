package com.henallux.smartcities.DAO;

import com.henallux.smartcities.model.CategoryService;
import com.henallux.smartcities.model.Service;
import com.henallux.smartcities.model.UserApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivierbeguin on 22/11/16.
 */

public interface IServiceDAO
{
    public ArrayList<Service> getServices(String token, String email) throws Exception;
    public void postServices(String token, Service service) throws Exception;
}
