package xyz.codingmentor.theme.park.entity;

import xyz.codingmentor.theme.park.dto.Address;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import xyz.codingmentor.theme.park.dto.ThemeParkDTO;

@Entity
public class ThemePark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1)
    private String name;

    @Embedded
    private Address address;

    private Integer stock;

    private Integer ticketPrice;

    @Column(name = "area_size")
    private Integer areaSize;

    @OneToMany(mappedBy = "ownerThemePark")
    private List<Machine> ownedMachines = new ArrayList<>();

    @OneToMany(mappedBy = "actualPark")
    @JoinTable(name = "theme_park_visitor",
            joinColumns = @JoinColumn(name = "theme_park_fk"),
            inverseJoinColumns = @JoinColumn(name = "visitor_fk"))
    private List<Visitor> visitors = new ArrayList<>();

    public ThemePark() {
        // default constructor
    }

    public ThemePark(Long id, String name, Address address, Integer stock, Integer ticketPrice, Integer areaSize) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.stock = stock;
        this.ticketPrice = ticketPrice;
        this.areaSize = areaSize;
    }

    public ThemePark(ThemeParkDTO themepark) {
        this.id = themepark.getId();
        this.name = themepark.getName();
        this.address = themepark.getAddress();
        this.stock = themepark.getStock();
        this.ticketPrice = themepark.getTicketPrice();
        this.areaSize = themepark.getAreaSize();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(Integer areaSize) {
        this.areaSize = areaSize;
    }

    public List<Machine> getOwnedMachines() {
        return ownedMachines;
    }

    public void setOwnedMachines(List<Machine> ownedMachines) {
        this.ownedMachines = ownedMachines;
    }

    public void addMachine(Machine machine) {
        this.ownedMachines.add(machine);
    }

    public void removeMachine(Machine machine) {
        this.ownedMachines.remove(machine);
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public void addVisitor(Visitor visitor) {
        this.visitors.add(visitor);
    }

    public void removeVisitor(Visitor visitor) {
        this.visitors.remove(visitor);
    }

    @Override
    public String toString() {
        return "ThemePark{" + "id=" + id + ", name=" + name + ", address=" + address + ", stock=" + stock + ", ticketPrice=" + ticketPrice + ", areaSize=" + areaSize + '}';
    }

}
