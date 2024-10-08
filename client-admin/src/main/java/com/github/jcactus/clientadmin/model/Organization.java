package com.github.jcactus.clientadmin.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Organization implements Serializable {
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

    @NotNull
    private String taxNumber;

    @NotNull
    private String regNumber;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;

    private String description;

    public Organization() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getInternationalName() {
        return internationalName;
    }

    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public Organization clone() throws CloneNotSupportedException {
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
