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
    public Service getServicesWithUser(UserApp userApp) throws Exception;
    public ArrayList<Service> getServices(String token) throws Exception;
    public void createService(Service service);
}
