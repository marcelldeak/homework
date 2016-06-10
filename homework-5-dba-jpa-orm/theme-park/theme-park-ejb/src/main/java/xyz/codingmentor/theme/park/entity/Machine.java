package xyz.codingmentor.theme.park.entity;

import xyz.codingmentor.theme.park.dto.MachineType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import xyz.codingmentor.theme.park.dto.MachineDTO;

@Entity
public class Machine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "machine_size")
    private Integer size;

    private Integer cost;

    private Integer ticketPrice;

    private Integer numberOfSeats;

    @Enumerated(EnumType.STRING)
    private MachineType type;

    private Integer requiredAge;

    @ManyToOne
    @JoinTable(name = "theme_park_machine",
            joinColumns = @JoinColumn(name = "theme_park_fk"),
            inverseJoinColumns = @JoinColumn(name = "machine_fk"))
    private ThemePark ownerThemePark;

    @OneToMany(mappedBy = "machine")
    private List<Visitor> visitorsRiding = new ArrayList<>();

    public Machine() {
        // default constructor
    }

    public Machine(Long id, String name, Integer size, Integer cost, Integer ticketPrice, Integer numberOfSeats, MachineType type, Integer requiredAge, ThemePark ownerThemePark) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.ticketPrice = ticketPrice;
        this.numberOfSeats = numberOfSeats;
        this.type = type;
        this.requiredAge = requiredAge;
        this.ownerThemePark = ownerThemePark;
    }

    public Machine(MachineDTO machine) {
        this.id = machine.getId();
        this.name = machine.getName();
        this.size = machine.getSize();
        this.cost = machine.getCost();
        this.ticketPrice = machine.getTicketPrice();
        this.numberOfSeats = machine.getNumberOfSeats();
        this.type = machine.getType();
        this.requiredAge = machine.getRequiredAge();

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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public MachineType getType() {
        return type;
    }

    public void setType(MachineType type) {
        this.type = type;
    }

    public Integer getRequiredAge() {
        return requiredAge;
    }

    public void setRequiredAge(Integer requiredAge) {
        this.requiredAge = requiredAge;
    }

    public List<Visitor> getVisitorsRiding() {
        return visitorsRiding;
    }

    public void setVisitorsRiding(List<Visitor> visitorsRiding) {
        this.visitorsRiding = visitorsRiding;
    }

    public ThemePark getOwnerThemePark() {
        return ownerThemePark;
    }

    public void setOwnerThemePark(ThemePark ownerThemePark) {
        this.ownerThemePark = ownerThemePark;
    }

    @Override
    public String toString() {
        return "Machine{" + "id=" + id + ", name=" + name + ", size=" + size + ", cost=" + cost + ", ticketPrice=" + ticketPrice + ", numberOfSeats=" + numberOfSeats + ", type=" + type + ", requiredAge=" + requiredAge + '}';
    }

}
