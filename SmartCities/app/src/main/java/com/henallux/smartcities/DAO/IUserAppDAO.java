package com.henallux.smartcities.DAO;

import com.henallux.smartcities.exception.ConnectionException;
import com.henallux.smartcities.model.UserApp;


public interface IUserAppDAO
{
    public String getUserWithMailandPw(String email, String pw) throws ConnectionException;
    public void registerUser(UserApp userApp) throws ConnectionException;
    public void updateUser(String token, UserApp userApp) throws  ConnectionException;
}
