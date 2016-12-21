package com.henallux.smartcities.DAO;


import com.henallux.smartcities.exception.ConnectionException;

public interface IGenericDAO
{
    public String getJsonStringWithURL(String token, String urlAdress) throws ConnectionException;
    public void postJsonStringWithURL(String token, String jsonString, String urlAdress) throws ConnectionException;
    public void putJsonStringWithURL(String token, String jsonString, String urlAdress) throws ConnectionException;
}
