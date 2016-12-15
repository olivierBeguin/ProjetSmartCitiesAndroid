package com.henallux.smartcities.DAO;

import com.henallux.smartcities.model.DoService;
import com.henallux.smartcities.model.Service;

import java.util.ArrayList;

/**
 * Created by oli07 on 10-12-16.
 */

public interface IDoServiceDAO
{
    public ArrayList<DoService> getDoServiceOfUser(String token, String username) throws Exception;
    public void postDoService(String token, DoService doService) throws Exception;
    public ArrayList<DoService> getDoServicesReceived(String token, String email) throws Exception;
    public void putDoService(String token, DoService doService) throws Exception;
}
