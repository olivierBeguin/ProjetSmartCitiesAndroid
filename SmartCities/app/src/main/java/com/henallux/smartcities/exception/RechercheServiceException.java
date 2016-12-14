package com.henallux.smartcities.exception;

/**
 * Created by olivierbeguin on 14/12/16.
 */

public class RechercheServiceException extends Exception
{
    private String text;

    public RechercheServiceException(String text)
    {
        this.text = text;
    }

    @Override
    public String getMessage()
    {
        return text;
    }
}
