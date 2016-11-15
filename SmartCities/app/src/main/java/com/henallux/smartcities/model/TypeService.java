package com.henallux.smartcities.model;


import java.io.Serializable;

public class TypeService implements Serializable
{
    private String label, description;

    public TypeService(String label, String description)
    {
        this.label = label;
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
}
