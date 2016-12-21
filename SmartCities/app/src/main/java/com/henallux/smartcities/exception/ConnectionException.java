package com.henallux.smartcities.exception;


public class ConnectionException extends Exception
{

    private boolean errorEntry;
    private String message;

    public ConnectionException(boolean errorEntry)
    {
        this.errorEntry = errorEntry;
    }
    public ConnectionException(String message)
    {
        this.message = message;
    }



    @Override
    public String getMessage()
    {
        if (!errorEntry)
        {
            return "La connexion avec la base de données a un problème. Veuillez réessayer plus tard.";
        }
        else
        {
            if(message.isEmpty())
            {
                return "Vos identifiants sont incorrects";
            }
            else
            {
                return message;
            }
        }

    }
}
