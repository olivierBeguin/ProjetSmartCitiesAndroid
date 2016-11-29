package com.henallux.smartcities.model;

import java.util.Date;

/**
 * Created by olivierbeguin on 22/11/16.
 */

public class DoService
{
    private Date dateService;
    private UserApp userDoService;
    private Comment commentOfService;
    private Service serviceDone;

    public DoService(Date dateService, UserApp userDoService, Comment commentOfService, Service serviceDone)
    {
        this.dateService = dateService;
        this.userDoService = userDoService;
        this.commentOfService =commentOfService;
        this.serviceDone = serviceDone;
    }

    public DoService(Date dateService, UserApp userDoService, Service serviceDone)
    {
        this.dateService = dateService;
        this.userDoService = userDoService;
        this.serviceDone = serviceDone;
    }

    public Date getDateService() { return dateService; }

    public Comment getCommentOfService() {
        return commentOfService;
    }

    public Service getServiceDone() {
        return serviceDone;
    }

    public UserApp getUserDoService() {
        return userDoService;
    }

    public void setCommentOfService(Comment commentOfService) {
        this.commentOfService = commentOfService;
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
}

