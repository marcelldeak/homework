package xyz.codingmentor.building.jpa.entity;

import xyz.codingmentor.building.jpa.dto.Person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "officeOwners",
                query = "select distinct owner from Office office, Owner owner where office.owner = owner"
        )
})
public class Owner extends Person implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    
    @OneToMany(mappedBy = "owner")
    @ElementCollection
    private List<Building> properties = new ArrayList();

    
    public Owner() {
        // required constructor
    }

    public Owner(String firstName, String lastName, Date dateOfBirth) {
        super(firstName,lastName);
        this.dateOfBirth = dateOfBirth;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Building> getOwnedBuildings() {
        return properties;
    }

    public void setOwnedBuildings(List<Building> ownedBuildings) {
        this.properties = ownedBuildings;
    }
    
    public void addOwnedBuildings(Building... buildings){
        properties.addAll(Arrays.asList(buildings));
    }

    @Override
    public String toString() {
        return "Owner{" + "id=" + id + ", firstName=" + getFirstname() + ", lastName=" + getLastname() + ", dateOfBirth=" + dateOfBirth + '}';
    }
        
}
