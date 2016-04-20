package hu.codingmentor.mobile.webshop.dto;

import hu.codingmentor.mobile.webshop.constraint.BirthBeforeRegistrate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import hu.codingmentor.mobile.webshop.annotation.Validable;
import java.io.Serializable;

@Validable
@BirthBeforeRegistrate
public class UserDTO implements Serializable{

    @NotNull
    @Size(min = 3)
    private String username;

    @NotNull
    @Size(min = 6)
    @Pattern.List({
        @Pattern(regexp = ".*[a-z]+.*"),
        @Pattern(regexp = ".*[A-Z]+.*"),
        @Pattern(regexp = ".*[0-9]+.*"),
        @Pattern(regexp = ".*[\\=\\+\\<\\>\\,\\.]+.*")
    })
    private String password;

    private String firstname;

    private String lastname;

    private LocalDate dateOfBirth;

    private LocalDate registrationDate = LocalDate.now();

    private Boolean admin = false;

    private transient List<MobileDTO> cart = new ArrayList();

    public UserDTO() {
        //default constructor
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
        this.registrationDate = LocalDate.now();
    }

    public UserDTO(String username, String password, LocalDate dateOfBirth) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usename) {
        this.username = usename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<MobileDTO> getCart() {
        return cart;
    }

    public void setCart(List<MobileDTO> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "User{" + "usename=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + ", dateOfBirth=" + dateOfBirth + ", registrationDate=" + registrationDate + ", admin=" + admin + ", cart=" + cart + '}';
    }

    public void addMobileToCart(MobileDTO mobile) {
        this.cart.add(mobile);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

}
