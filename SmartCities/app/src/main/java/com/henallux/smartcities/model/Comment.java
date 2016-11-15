package com.henallux.smartcities.model;

import java.io.Serializable;

public class Comment implements Serializable
{
    private String comment;
    private Double rating;

    public Comment(String comment, Double rating)
    {
        this.comment = comment;
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}
