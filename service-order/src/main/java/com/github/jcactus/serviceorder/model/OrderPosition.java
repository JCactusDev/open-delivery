package com.github.jcactus.serviceorder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "client_order_position")
public class OrderPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "count")
    private double count;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Order order;

    @Override
    public boolean equals(Object otherObject) {
        // Проверка объектов на идентичность
        if (this == otherObject) {
            return true;
        }
        // Проверка явного параметра == null
        if (otherObject == null) {
            return false;
        }
        // Проверка совпадения классов
        if (this.getClass() != otherObject.getClass()) {
            return false;
        }
        // Приведение otherObject к типу текущего класа
        OrderPosition other = (OrderPosition) otherObject;
        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(id, other.id)
                && Objects.equals(productId, other.productId)
                && count == other.count
                && price == other.price
                && Objects.equals(order, other.order);
    }

    @Override
    public int hashCode() {
        return 31 * ((id == null) ? 0 : id.hashCode())
                + 31 * ((productId == null) ? 0 : productId.hashCode())
                + 31 * Double.valueOf(count).hashCode()
                + 31 * Double.valueOf(price).hashCode()
                + 31 * ((order == null) ? 0 : order.hashCode());
    }

    @Override
    public String toString() {
        return String.format("OrderPosition [id=%s, productId=%s, count=%s, price=%s]",
                id, productId, count, price
        );
    }

    @Override
    public OrderPosition clone() throws CloneNotSupportedException {
        OrderPosition cloneObject;
        try {
            cloneObject = (OrderPosition) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new OrderPosition();
        }
        cloneObject.productId = productId;
        cloneObject.count = count;
        cloneObject.price = price;
        return cloneObject;
    }

}
