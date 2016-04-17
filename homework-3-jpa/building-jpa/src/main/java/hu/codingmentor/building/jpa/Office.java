package hu.codingmentor.building.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Office extends Building implements Serializable{
    
    @Column(name = "number_of_companies")
    private Integer numberOfCompanies;
    
    @Column(name = "has_parking_lot")
    private Boolean hasParkingLot;

    public Office() {
        // required constructor
    }

    public Office(Integer numberOfCompanies, Boolean hasParkingLot, Address address, String name, Integer numberOfRooms, Owner owner) {
        super(address, name, numberOfRooms, owner);
        this.numberOfCompanies = numberOfCompanies;
        this.hasParkingLot = hasParkingLot;
    }

    public Integer getNumberOfCompanies() {
        return numberOfCompanies;
    }

    public void setNumberOfCompanies(Integer numberOfCompanies) {
        this.numberOfCompanies = numberOfCompanies;
    }

    public Boolean getHasParkingLot() {
        return hasParkingLot;
    }

    public void setHasParkingLot(Boolean hasParkingLot) {
        this.hasParkingLot = hasParkingLot;
    }

    @Override
    public String toString() {
        return "Office{" + super.toString() + ", numberOfCompanies=" + numberOfCompanies + ", hasParkingLot=" + hasParkingLot + '}';
    }
    
}
