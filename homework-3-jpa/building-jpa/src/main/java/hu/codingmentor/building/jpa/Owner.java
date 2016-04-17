package hu.codingmentor.building.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "officeOwners",
                query = "select distinct owner from Office office, Owner owner where office.owner = owner"
        )
})
@SecondaryTable(name = "properties")
public class Owner implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    
    @OneToMany(mappedBy = "owner")
    private List<Building> properties = new ArrayList();

    
    public Owner() {
        // required constructor
    }

    public Owner(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return "Owner{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + '}';
    }
        
}
