package com.github.jcactus.serviceorganization.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "organizations")
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @Column(name = "short_name")
    private String shortName;

    @Column(name = "international_name")
    private String internationalName;

    @NotNull
    @Column(name = "type")
    private OrganizationType type;

    @NotNull
    @Column(name = "tax_number")
    private String taxNumber;

    @NotNull
    @Column(name = "reg_number")
    private String regNumber;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "reg_date")
    private LocalDate regDate;

    @Column(name = "description")
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
        Organization other = (Organization) otherObject;
        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(id, other.id)
                && Objects.equals(name, other.name)
                && Objects.equals(fullName, other.fullName)
                && Objects.equals(shortName, other.shortName)
                && Objects.equals(internationalName, other.internationalName)
                && Objects.equals(type, other.type)
                && Objects.equals(taxNumber, other.taxNumber)
                && Objects.equals(regNumber, other.regNumber)
                && Objects.equals(regDate, other.regDate)
                && Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, shortName, internationalName, type, taxNumber, regNumber, regDate, description);
    }

    @Override
    public String toString() {
        return String.format("Organization [id=%s, name=%s, fullName=%s, shortName=%s, internationalName=%s, type=%s, taxNumber=%s, regNumber=%s, regDate=%s, description=%s]",
                id, name, fullName, shortName, internationalName, type, taxNumber, regNumber, regDate, description
        );
    }

    @Override
    public Organization clone() {
        Organization cloneObject;
        try {
            cloneObject = (Organization) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new Organization();
        }
        cloneObject.name = name;
        cloneObject.fullName = fullName;
        cloneObject.shortName = shortName;
        cloneObject.internationalName = internationalName;
        cloneObject.type = type;
        cloneObject.taxNumber = taxNumber;
        cloneObject.regNumber = regNumber;
        cloneObject.regDate = regDate;
        cloneObject.description = description;
        return cloneObject;
    }

}