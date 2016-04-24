package xyz.codingmentor.building.jpa.entity;

import xyz.codingmentor.building.jpa.dto.Address;
import xyz.codingmentor.building.jpa.type.RestaurantType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Restaurant extends Building implements Serializable{
    
    @Column(name = "is_open")
    private Boolean isOpen;
    
    @Column(name = "number_of_emplyees")
    private Integer numberOfEmployees;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "restaurant_type")
    private RestaurantType type;

    public Restaurant() {
        // required constructor
    }

    public Restaurant(Boolean isOpen, Integer numberOfEmployees, RestaurantType type, Address address, String name, Integer numberOfRooms, Owner owner) {
        super(address, name, numberOfRooms, owner);
        this.isOpen = isOpen;
        this.numberOfEmployees = numberOfEmployees;
        this.type = type;
    }
    
    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Restaurant{" + super.toString() + ", isOpen=" + isOpen + ", numberOfEmployees=" + numberOfEmployees + ", type=" + type + '}';
    }

}
