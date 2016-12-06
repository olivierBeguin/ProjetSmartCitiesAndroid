package com.henallux.smartcities.DAO;

import com.henallux.smartcities.exception.ConnectionException;
import com.henallux.smartcities.model.UserApp;

/**
 * Created by olivierbeguin on 22/11/16.
 */

public interface IUserAppDAO
{
    public String getUserWithMailandPw(String email, String pw) throws ConnectionException;
    public void registerUser(UserApp userApp) throws Exception;
    public void updateUser(UserApp userApp);
}
