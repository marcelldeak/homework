
package xyz.codingmentor.theme.park.entity;

import xyz.codingmentor.theme.park.dto.Address;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ThemePark implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    
    @NotNull
    @Size(min = 1)
    private String name;
    
    @NotNull
    @Embedded
    private Address address;
    
    @NotNull
    private Integer stock;
    
    @NotNull
    @Min(1)
    private Integer ticketPrice;
    
    @Min(1)
    @Column(name = "area_size")
    private Double areaSize;

    @OneToMany
    @JoinTable(name = "park_machine",
            joinColumns = @JoinColumn(name = "park_fk"),
            inverseJoinColumns = @JoinColumn(name = "machine_fk"))
    private List<Machine> ownedMachines = new ArrayList<>();
    
    public ThemePark() {
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

    public Double getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(Double areaSize) {
        this.areaSize = areaSize;
    }

    public List<Machine> getOwnedMachines() {
        return ownedMachines;
    }

    public void setOwnedMachines(List<Machine> ownedMachines) {
        this.ownedMachines = ownedMachines;
    }
    
    public void addMachine(Machine machine){
        this.ownedMachines.add(machine);
    }

    @Override
    public String toString() {
        return "ThemePark{" + "id=" + id + ", name=" + name + ", address=" + address + ", stock=" + stock + ", ticketPrice=" + ticketPrice + ", areaSize=" + areaSize + '}';
    }
    
}
