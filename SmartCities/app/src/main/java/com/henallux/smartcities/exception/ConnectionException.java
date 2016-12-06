package com.henallux.smartcities.exception;

/**
 * Created by olivierbeguin on 6/12/16.
 */

public class ConnectionException extends Exception
{
    public ConnectionException()
    {

    }

    @Override
    public String getMessage()
    {
        return "Nous ne parvenons pas à effection la connection. Etes-vous bien connecté à Internet";
    }
}
