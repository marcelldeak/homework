package xyz.codingmentor.building.jpa.entity;

import xyz.codingmentor.building.jpa.dto.Address;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class House extends Building implements Serializable{
    
    @Column(name = "number_of_inmates")
    private Integer numberOfInmates;
    
    @Column(name = "condition_per_cent")
    private Double conditionPerCent;

    public House() {
        // required constructor
    }

    public House(Address address, String name, Integer numberOfRooms, Owner owner, Integer numberOfInmates, Double conditionPerCent) {
        super(address, name, numberOfRooms, owner);
        this.numberOfInmates = numberOfInmates;
        this.conditionPerCent = conditionPerCent;
    }

    public Integer getNumberOfInmates() {
        return numberOfInmates;
    }

    public void setNumberOfInmates(Integer numberOfInmates) {
        this.numberOfInmates = numberOfInmates;
    }

    public Double getCondition() {
        return conditionPerCent;
    }

    public void setCondition(Double condition) {
        this.conditionPerCent = condition;
    }

    @Override
    public String toString() {
        return "House{" + super.toString() + ", numberOfInmates=" + numberOfInmates + ", conditionPerCent=" + conditionPerCent + '}';
    }

}
