package com.github.jcactus.serviceorder.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "client_id")
    private Long clientId;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderPosition> positions;

    public void addPosition(OrderPosition position) {
        position.setOrder(this);
        positions.add(position);
    }

    public void removePosition(OrderPosition position) {
        position.setOrder(null);
        positions.remove(position);
    }
    
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
        Order other = (Order) otherObject;
        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(id, other.id)
                && Objects.equals(organizationId, other.organizationId)
                && Objects.equals(clientId, other.clientId)
                && Objects.equals(positions, other.positions);
    }

    @Override
    public int hashCode() {
        return 31 * ((id == null) ? 0 : id.hashCode())
                + 31 * ((organizationId == null) ? 0 : organizationId.hashCode())
                + 31 * ((clientId == null) ? 0 : clientId.hashCode())
                + 31 * ((positions == null) ? 0 : positions.hashCode());
    }

    @Override
    public String toString() {
        return String.format("Order [id=%s, organizationId=%s, clientId=%s, positions=%s]",
                id, organizationId, clientId, positions
        );
    }

    @Override
    public Order clone() throws CloneNotSupportedException {
        Order cloneObject;
        try {
            cloneObject = (Order) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new Order();
        }
        cloneObject.organizationId = organizationId;
        cloneObject.clientId = clientId;
        cloneObject.positions = new ArrayList<>();
        for (OrderPosition position : positions) {
            cloneObject.positions.add(position.clone());
        }
        return cloneObject;
    }

}