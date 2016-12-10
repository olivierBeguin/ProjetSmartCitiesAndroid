package com.henallux.smartcities.exception;

/**
 * Created by olivierbeguin on 6/12/16.
 */

public class ConnectionException extends Exception
{
    private boolean errorEntry;

    public ConnectionException(boolean errorEntry)
    {
        this.errorEntry = errorEntry;
    }

    @Override
    public String getMessage()
    {
        if (errorEntry == false)
            return "Nous ne parvenons pas à effectuer la connection. Etes-vous bien connecté à Internet?";
        else
            return "Vos identifiants sont incorrects";
    }
}
