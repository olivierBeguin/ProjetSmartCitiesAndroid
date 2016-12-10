package com.henallux.smartcities.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserApp implements Serializable
{
    private String FirstName, LastName, Password, Email, PhoneNumber, Street, City, Country, PostalCode, Number, Category;
    private Date DateInscription;
    private Integer SumServiceDone, SumServiceGiven;

    public UserApp(String firstName, String lastName, String password, String email, String phoneNumber, String street, String city, String country, String category, Date dateInscription, String postalCode, String number, Integer sumServiceDone, Integer sumServiceGiven)
    {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Password = password;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.PostalCode = postalCode;
        this.Street = street;
        this.City = city;
        this.Country = country;
        this.Category = category;
        this.DateInscription = dateInscription;
        this.Number = number;
        this.SumServiceDone = sumServiceDone;
        this.SumServiceGiven = sumServiceGiven;
    }

    public UserApp(String firstName, String lastName, String password, String email, String phoneNumber, String street, String city, String country, String category, Date dateInscription, String postalCode, String number)
    {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Password = password;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.PostalCode = postalCode;
        this.Street = street;
        this.City = city;
        this.Country = country;
        this.Category = category;
        this.DateInscription = dateInscription;
        this.Number = number;
        this.SumServiceDone = 0;
        this.SumServiceGiven = 0;
    }

    public UserApp(String firstName, String lastName, String email, String phoneNumber, String street, String city, String country, String category, Date dateInscription, String postalCode, String number, Integer sumServiceDone, Integer sumServiceGiven)
    {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.PostalCode = postalCode;
        this.Street = street;
        this.City = city;
        this.Country = country;
        this.Category = category;
        this.DateInscription = dateInscription;
        this.Number = number;
        this.SumServiceDone = sumServiceDone;
        this.SumServiceGiven = sumServiceGiven;
    }

    public UserApp()
    {

    }

    public String getFirstName()
    {
        return FirstName;
    }

    public void setFirstName(String firstName)
    {
        this.FirstName = firstName;
    }

    public String getLastName()
    {
        return LastName;
    }

    public void setLastName(String lastName)
    {
        this.LastName = lastName;
    }

    public String getPassword()
    {
        return Password;
    }

    public void setPassword(String password)
    {
        this.Password = password;
    }

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        this.Email = email;
    }

    public String getPhoneNumber()
    {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.PhoneNumber = phoneNumber;
    }

    public String getStreet()
    {
        return Street;
    }

    public void setStreet(String street)
    {
        this.Street = street;
    }

    public String getCity()
    {
        return City;
    }

    public void setCity(String city)
    {
        this.City = city;
    }

    public String getCountry()
    {
        return Country;
    }

    public void setCountry(String country)
    {
        this.Country = country;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public Date getDateInscription()
    {
        return DateInscription;
    }

    public void setDateInscription(Date dateInscription)
    {
        this.DateInscription = dateInscription;
    }

    public String getPostalCode()
    {
        return PostalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.PostalCode = postalCode;
    }

    public String getNumber()
    {
        return Number;
    }

    public void setNumber(String number)
    {
        this.Number = number;
    }

    public Integer getSumServiceDone()
    {
        return SumServiceDone;
    }

    public void setSumServiceDone(Integer sumServiceDone)
    {
        this.SumServiceDone = sumServiceDone;
    }

    public Integer getSumServiceGiven()
    {
        return SumServiceGiven;
    }

    public void setSumServiceGiven(Integer sumServiceGiven) { this.SumServiceGiven = sumServiceGiven; }

    @Override
    public String toString() {
        return "Je suis "+ FirstName;
    }


}
