package com.henallux.smartcities.DAO;

import com.henallux.smartcities.exception.ConnectionException;
import com.henallux.smartcities.model.DoService;
import java.util.ArrayList;



public interface IDoServiceDAO
{
    public ArrayList<DoService> getDoServiceOfUser(String token, String username) throws ConnectionException;
    public void postDoService(String token, DoService doService) throws ConnectionException;
    public ArrayList<DoService> getDoServicesReceived(String token, String email) throws ConnectionException;
    public void putDoService(String token, DoService doService) throws ConnectionException;
}
