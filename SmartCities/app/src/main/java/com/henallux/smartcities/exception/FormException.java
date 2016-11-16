package com.henallux.smartcities.exception;

public class FormException extends Exception
{
    private String text;

    public FormException(String text)
    {
        this.text = text;
    }

    @Override
    public String getMessage()
    {
        return text;
    }
}
