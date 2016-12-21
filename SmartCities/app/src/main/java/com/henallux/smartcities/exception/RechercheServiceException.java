package com.henallux.smartcities.exception;


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
