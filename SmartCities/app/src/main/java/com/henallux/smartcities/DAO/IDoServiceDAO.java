package com.henallux.smartcities.DAO;

import com.henallux.smartcities.model.DoService;

import java.util.ArrayList;

/**
 * Created by oli07 on 10-12-16.
 */

public interface IDoServiceDAO
{
    public ArrayList<DoService> getDoServiceOfUser(String token, String username) throws Exception;
}
