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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Machine implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 1)
    private String name;

    @Min(1)
    @Column(name = "machine_size")
    private Integer size;

    @Min(1)
    private Integer cost;

    @Min(1)
    private Integer ticketPrice;

    @Min(1)
    private Integer numberOfSeats;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MachineType type;

    @Min(1)
    private Integer requiredAge;

    @OneToMany
    @JoinTable(name = "machine_visitor",
            joinColumns = @JoinColumn(name = "machine_fk"),
            inverseJoinColumns = @JoinColumn(name = "visitor_fk"))
    private List<Visitor> visitorsRiding = new ArrayList<>();

    public Machine() {
        // default constructor
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

    @Override
    public String toString() {
        return "Machine{" + "id=" + id + ", name=" + name + ", size=" + size + ", cost=" + cost + ", ticketPrice=" + ticketPrice + ", numberOfSeats=" + numberOfSeats + ", type=" + type + ", requiredAge=" + requiredAge + '}';
    }

}
