package xyz.codingmentor.building.jpa.entity;

import xyz.codingmentor.building.jpa.dto.Address;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(
                name = "buildingsFromBudapest",
                query = "select b from Building b where b.address.zipcode like '1%'"
        ),
        @NamedQuery(
                name = "smallHouses",
                query = "select h from House h where h.numberOfRooms < 3"
        ),
        @NamedQuery(
                name = "findBuildingByName",
                query = "select b from Building b where b.name like :name"
        ),
        @NamedQuery(
                name = "addressesOfBufes",
                query = "select distinct r.address from Restaurant r where r.type like 'BUFE'"
        )
})
public class Building implements Serializable {
    
    @Id
    @GeneratedValue
    protected Long id;
    
    @Embedded
    protected Address address;
    
    protected String name;
    
    @Column(name = "number_of_rooms")
    protected Integer numberOfRooms;
    
    @ManyToOne
    @JoinTable(name = "building_owner_joint",joinColumns = @JoinColumn(name = "building_fk"),
            inverseJoinColumns = @JoinColumn(name = "owner_fk"))
    private Owner owner;
    
    public Building() {
        // required constructor
    }

    public Building(Address address, String name, Integer numberOfRooms, Owner owner) {
        this.address = address;
        this.name = name;
        this.numberOfRooms = numberOfRooms;
        this.owner = owner;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id + ", address=" + address + ", name=" + name + ", numberOfRooms=" + numberOfRooms + ", owner=" + owner;
    }

}
