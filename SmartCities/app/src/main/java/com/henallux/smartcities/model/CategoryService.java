package com.henallux.smartcities.model;


import java.io.Serializable;

public class CategoryService implements Serializable
{
    private String description;

    public CategoryService(String description)
    {
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

}
