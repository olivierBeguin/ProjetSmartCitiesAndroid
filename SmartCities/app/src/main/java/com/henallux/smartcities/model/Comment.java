package com.henallux.smartcities.model;

import java.io.Serializable;

public class Comment implements Serializable
{
    private String commentDescription;
    private Double rating;
    private DoService doServiceComment;

    public Comment(String commentDescription, Double rating, DoService doServiceComment)
    {
        this.commentDescription = commentDescription;
        this.rating = rating;
        this.doServiceComment = doServiceComment;
    }

    public String getComment() {
        return commentDescription;
    }

    public void setComment(String commentDescription) { this.commentDescription = commentDescription; }

    public Double getRating() { return rating; }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public DoService getDoServiceComment() {
        return doServiceComment;
    }

    public void setDoServiceComment(DoService doServiceComment) {
        this.doServiceComment = doServiceComment;
    }
}
