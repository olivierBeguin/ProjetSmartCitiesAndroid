package Model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable
{
    private String firstname, lastname, password, mailAdress, phoneNumber, street, city, country;
    private Date inscriptionDate;
    private Integer postalCode, houseNumber, sumServiceDone, sumServiceGiven;

    public User(String firstname, String lastname, String password, String mailAdress, String phoneNumber, String street, String city, String country, Date inscriptionDate, Integer postalCode, Integer houseNumber, Integer sumServiceDone, Integer sumServiceGiven)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.mailAdress = mailAdress;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.street = street;
        this.city = city;
        this.country = country;
        this.inscriptionDate = inscriptionDate;
        this.houseNumber = houseNumber;
        this.sumServiceDone = sumServiceDone;
        this.sumServiceGiven = sumServiceGiven;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getMailAdress()
    {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress)
    {
        this.mailAdress = mailAdress;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public Date getInscriptionDate()
    {
        return inscriptionDate;
    }

    public void setInscriptionDate(Date inscriptionDate)
    {
        this.inscriptionDate = inscriptionDate;
    }

    public Integer getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode)
    {
        this.postalCode = postalCode;
    }

    public Integer getHouseNumber()
    {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    public Integer getSumServiceDone()
    {
        return sumServiceDone;
    }

    public void setSumServiceDone(Integer sumServiceDone)
    {
        this.sumServiceDone = sumServiceDone;
    }

    public Integer getSumServiceGiven()
    {
        return sumServiceGiven;
    }

    public void setSumServiceGiven(Integer sumServiceGiven)
    {
        this.sumServiceGiven = sumServiceGiven;
    }
}
