package com.henallux.smartcities.DAO;

/**
 * Created by olivierbeguin on 5/12/16.
 */

public interface IGenericDAO
{
    public String getJsonStringWithURL(String token, String urlAdress) throws Exception;
    public void postJsonStringWithURL(String token, String jsonString, String urlAdress) throws Exception;
}
