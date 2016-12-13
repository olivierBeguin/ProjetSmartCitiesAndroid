package com.henallux.smartcities.model;

import java.util.Date;

/**
 * Created by olivierbeguin on 22/11/16.
 */

public class DoService
{
    private Date dateService;
    private UserApp userDoService;
    private Service serviceDone;
    private String comment;
    private Double rating;

    public DoService(Date dateService, UserApp userDoService, Service serviceDone, String comment, Double rating)
    {
        this.dateService = dateService;
        this.userDoService = userDoService;
        this.serviceDone = serviceDone;
        this.comment = comment;
        this.rating = rating;
    }

    public Date getDateService() { return dateService; }

    public Service getServiceDone() {
        return serviceDone;
    }

    public UserApp getUserDoService() {
        return userDoService;
    }

    public String getComment() {
        return comment;
    }

    public Double getRating() {
        return rating;
    }

    public void setDateService(Date dateService) {
        this.dateService = dateService;
    }

    public void setServiceDone(Service serviceDone) {
        this.serviceDone = serviceDone;
    }

    public void setUserDoService(UserApp userDoService) {
        this.userDoService = userDoService;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}

