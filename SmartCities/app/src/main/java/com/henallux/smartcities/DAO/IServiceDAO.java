package com.henallux.smartcities.DAO;

import com.henallux.smartcities.exception.ConnectionException;
import com.henallux.smartcities.model.Service;
import java.util.ArrayList;


public interface IServiceDAO
{
    public ArrayList<Service> getServices(String token, String email) throws ConnectionException;
    public void postServices(String token, Service service) throws ConnectionException;
    public void putService(String token, Service service) throws ConnectionException;

}
