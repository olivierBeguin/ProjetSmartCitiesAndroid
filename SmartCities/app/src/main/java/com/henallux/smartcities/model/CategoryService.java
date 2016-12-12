package com.henallux.smartcities.model;


import java.io.Serializable;

public class CategoryService implements Serializable
{
    private String label;
    private Integer id;

    public CategoryService(Integer id, String label)
    {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    @Override
    public String toString()
    {
        return label;
    }
}
