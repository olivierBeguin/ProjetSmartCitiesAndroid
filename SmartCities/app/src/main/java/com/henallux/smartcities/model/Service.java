package com.henallux.smartcities.model;


import java.io.Serializable;
import java.util.Date;

public class Service implements Serializable
{
    private String descriptionService, labelService;
    private Date datePublicationService;
    private UserApp UserNeedService;
    private CategoryService category;
    private DoService doService;

    public  Service(String labelService, String descriptionService, Date datePublicationService)
    {
        this.descriptionService = descriptionService;
        this.labelService = labelService;
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

    public DoService getDoService() {
        return doService;
    }

    public String getLabelService() {
        return labelService;
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

    public void setDoService(DoService doService) {
        this.doService = doService;
    }

    public void setLabelService(String labelService) {
        this.labelService = labelService;
    }
}

