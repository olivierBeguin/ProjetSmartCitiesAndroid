package com.henallux.smartcities.model;


import java.io.Serializable;
import java.util.Date;

public class Service implements Serializable
{
    private Integer id;
    private String descriptionService, labelService;
    private Date datePublicationService;
    private Boolean serviceDone;
    private UserApp UserNeedService;
    private CategoryService category;
    private DoService doService;

    public  Service(Integer id, String labelService, String descriptionService, Date datePublicationService)
    {
        this.id = id;
        this.descriptionService = descriptionService;
        this.labelService = labelService;
        this.datePublicationService = datePublicationService;
        this.serviceDone = false;
    }

    public  Service(String labelService, String descriptionService, Date datePublicationService, UserApp userNeedService, CategoryService categoryService)
    {
        this.descriptionService = descriptionService;
        this.labelService = labelService;
        this.datePublicationService = datePublicationService;
        this.serviceDone = false;
        this.UserNeedService = userNeedService;
        this.category = categoryService;
    }

    public Integer getId() {
        return id;
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

    public Boolean getServiceDone() {
        return serviceDone;
    }

    public void setCategory(CategoryService category) {
        this.category = category;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setServiceDone(Boolean serviceDone) {
        this.serviceDone = serviceDone;
    }
}

