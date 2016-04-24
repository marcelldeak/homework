

package xyz.codingmentor.building.jpa.dto;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {
    private String firstname;
    
    private String lastname;

    public Person() {
        // default constructor
    }

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    
}
