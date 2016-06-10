package xyz.codingmentor.theme.park.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import xyz.codingmentor.theme.park.entity.ThemePark;

public class ThemeParkDTO {

    private Long id;

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    private Address address;

    @NotNull
    private Integer stock;

    @NotNull
    @Min(1)
    private Integer ticketPrice;

    @Min(1)
    private Integer areaSize;

    public ThemeParkDTO() {
        // default constructor
    }

    public ThemeParkDTO(Long id, String name, Address address, Integer stock, Integer ticketPrice, Integer areaSize) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.stock = stock;
        this.ticketPrice = ticketPrice;
        this.areaSize = areaSize;
    }

    public ThemeParkDTO(ThemePark themepark) {
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

    @Override
    public String toString() {
        return "ThemeParkDTO{" + "id=" + id + ", name=" + name + ", address=" + address + ", stock=" + stock + ", ticketPrice=" + ticketPrice + ", areaSize=" + areaSize + '}';
    }

}
