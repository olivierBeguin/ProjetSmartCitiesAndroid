package com.henallux.smartcities.model;

import java.io.Serializable;

public class Comment implements Serializable
{
    private String commentDescription;
    private Double rating;

    public Comment(String commentDescription, Double rating)
    {
        this.commentDescription = commentDescription;
        this.rating = rating;
    }

    public String getComment() {
        return commentDescription;
    }

    public void setComment(String commentDescription) { this.commentDescription = commentDescription; }

    public Double getRating() { return rating; }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}
