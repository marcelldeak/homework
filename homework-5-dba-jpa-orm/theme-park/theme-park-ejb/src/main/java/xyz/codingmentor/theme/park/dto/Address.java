package xyz.codingmentor.theme.park.dto;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Address implements Serializable {

    @NotNull
    @Pattern(regexp = "[A-Z]{1}-\\d{4}")
    private String zipcode;

    @NotNull
    @Size(min = 1)
    private String country;

    @NotNull
    @Size(min = 1)
    private String city;

    @NotNull
    @Size(min = 1)
    private String street;

    @Pattern(regexp = "[1-9]+\\d*/[A-Z]*")
    private String number;

    public Address() {
        //default constructor
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" + "zipcode=" + zipcode + ", country=" + country + ", city=" + city + ", street=" + street + ", number=" + number + '}';
    }

}
