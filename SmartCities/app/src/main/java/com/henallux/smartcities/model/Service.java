package com.henallux.smartcities.model;


import java.io.Serializable;
import java.util.Date;

public class Service implements Serializable
{
    private String descriptionService;
    private Date datePublicationService;
    private UserApp UserNeedService;
    private CategoryService category;

    public  Service(String descriptionService, Date datePublicationService, UserApp userNeedService, CategoryService category)
    {
        this.descriptionService = descriptionService;
        this.datePublicationService = datePublicationService;
        this.UserNeedService = userNeedService;
        this.category = category;
    }

    public  Service(String descriptionService, Date datePublicationService)
    {
        this.descriptionService = descriptionService;
        this.datePublicationService = datePublicationService;
    }

    public CategoryService getCategory() {
        return category;
    }

    public Date getDatePublicationService() {
        return datePublicationService;
    }

    public String getDescriptionService() {
        return descriptionService;
    }

    public UserApp getUserNeedService() {
        return UserNeedService;
    }

    public void setCategory(CategoryService category) {
        this.category = category;
    }

    public void setDatePublicationService(Date datePublicationService) {
        this.datePublicationService = datePublicationService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    public void setUserNeedService(UserApp userNeedService) {
        UserNeedService = userNeedService;
    }
}

