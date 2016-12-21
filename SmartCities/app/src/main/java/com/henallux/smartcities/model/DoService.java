package com.henallux.smartcities.model;

import java.io.Serializable;
import java.util.Date;


public class DoService implements Serializable
{
    private Integer id;
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

    public DoService(Integer id, Date dateService, UserApp userDoService, Service serviceDone, String comment, Double rating)
    {
        this.id = id;
        this.dateService = dateService;
        this.userDoService = userDoService;
        this.serviceDone = serviceDone;
        this.comment = comment;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
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

