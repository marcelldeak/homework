package hu.codingmentor.mobile.webshop.dto;

import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import hu.codingmentor.mobile.webshop.annotation.Validable;

@Validable
public class MobileDTO {

    private String id;

    @NotNull
    @Size(min = 3)
    private String type;

    @NotNull
    @Size(min = 3)
    private String manufacturer;

    @Min(1)
    private Integer price;

    @Min(0)
    private Integer piece;

    public MobileDTO() {
        this.id = UUID.randomUUID().toString();
    }

    public MobileDTO(String type, String manufacturer, Integer price, Integer piece) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
        this.piece = piece;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "Mobile{" + "id=" + id + ", type=" + type + ", manufacturer=" + manufacturer + ", price=" + price + ", piece=" + piece + '}';
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
        final MobileDTO other = (MobileDTO) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.manufacturer);
        hash = 37 * hash + Objects.hashCode(this.price);
        return hash;
    }
}
