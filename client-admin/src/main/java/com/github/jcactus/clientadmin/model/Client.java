package com.github.jcactus.clientadmin.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String fullName;

    @NotNull
    private String shortName;

    private String internationalName;

    @NotNull
    private OrganizationType type;

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
        Client other = (Client) otherObject;
        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(id, other.id)
                && Objects.equals(name, other.name)
                && Objects.equals(fullName, other.fullName)
                && Objects.equals(shortName, other.shortName)
                && Objects.equals(internationalName, other.internationalName)
                && Objects.equals(type, other.type)
                && Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, shortName, internationalName, type, description);
    }

    @Override
    public String toString() {
        return String.format("Organization [id=%s, name=%s, fullName=%s, shortName=%s, internationalName=%s, type=%s, description=%s]",
                id, name, fullName, shortName, internationalName, type, description
        );
    }

    @Override
    public Client clone() throws CloneNotSupportedException {
        Client cloneObject;
        try {
            cloneObject = (Client) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new Client();
        }
        cloneObject.name = name;
        cloneObject.fullName = fullName;
        cloneObject.shortName = shortName;
        cloneObject.internationalName = internationalName;
        cloneObject.type = type;
        cloneObject.description = description;
        return cloneObject;
    }

}
