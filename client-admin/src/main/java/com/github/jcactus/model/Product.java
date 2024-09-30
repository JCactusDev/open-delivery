package com.github.jcactus.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //@NotNull
    private String name;

    private String description;

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
        Product other = (Product) otherObject;
        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(id, other.id)
                && Objects.equals(name, other.name)
                && Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return String.format("Product [id=%s, name=%s, description=%s]",
                id, name, description
        );
    }

    @Override
    public Product clone() {
        Product cloneObject;
        try {
            cloneObject = (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new Product();
        }
        cloneObject.name = name;
        cloneObject.description = description;
        return cloneObject;
    }
}
