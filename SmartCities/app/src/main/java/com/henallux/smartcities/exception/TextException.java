package com.henallux.smartcities.exception;

public class TextException extends Exception
{
    private String text;

    public TextException(String text)
    {
        this.text = text;
    }

    @Override
    public String getMessage()
    {
        return text;
    }
}
