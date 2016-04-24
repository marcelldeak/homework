package xyz.codingmentor.building.jpa.dto;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {
    
    private String zipcode;
    
    private String city;
    
    private String street;

    public Address() {
        // required constructor
    }

    public Address(String zipcode, String city, String street) {
        this.zipcode = zipcode;
        this.city = city;
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

    @Override
    public String toString() {
        return "Address{" + "zipcode=" + zipcode + ", city=" + city + ", street=" + street + '}';
    }
    
    
}
