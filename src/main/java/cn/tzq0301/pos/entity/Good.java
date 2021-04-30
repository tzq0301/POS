package cn.tzq0301.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author TZQ
 * @Description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good implements Serializable {
    private static final long serialVersionUID = 5313082670206721825L;

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    public void discount(BigDecimal discount) {
        price = price.multiply(discount);
    }

    public String getInformation() {
        return "id = " + id + "\tname = " + name + "\tdescription = " + description + "\tprice = " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Good good = (Good) o;
        return id.equals(good.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Good{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
