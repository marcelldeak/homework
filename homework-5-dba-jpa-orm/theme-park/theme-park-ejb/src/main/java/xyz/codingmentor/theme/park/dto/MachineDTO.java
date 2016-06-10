package xyz.codingmentor.theme.park.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import xyz.codingmentor.theme.park.entity.Machine;

public class MachineDTO {

    private Long id;

    @NotNull
    @Size(min = 1)
    private String name;

    @Min(1)
    private Integer size;

    @Min(1)
    private Integer cost;

    @Min(1)
    private Integer ticketPrice;

    @Min(1)
    private Integer numberOfSeats;

    @NotNull
    private MachineType type;

    @Min(1)
    private Integer requiredAge;

    public MachineDTO() {
        // default constructor
    }

    public MachineDTO(Long id, String name, Integer size, Integer cost, Integer ticketPrice, Integer numberOfSeats, MachineType type, Integer requiredAge, ThemeParkDTO ownerThemePark) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.ticketPrice = ticketPrice;
        this.numberOfSeats = numberOfSeats;
        this.type = type;
        this.requiredAge = requiredAge;
    }

    public MachineDTO(Machine machine) {
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

    @Override
    public String toString() {
        return "MachineDTO{" + "id=" + id + ", name=" + name + ", size=" + size + ", cost=" + cost + ", ticketPrice=" + ticketPrice + ", numberOfSeats=" + numberOfSeats + ", type=" + type + ", requiredAge=" + requiredAge + '}';
    }
}
