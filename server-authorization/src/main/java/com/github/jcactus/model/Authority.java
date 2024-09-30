package com.github.jcactus.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Data
public class Authority implements GrantedAuthority {

    private Long id;

    @NotNull
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
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
        Authority other = (Authority) otherObject;
        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(id, other.id)
                && Objects.equals(authority, other.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }

    @Override
    public String toString() {
        return String.format("Authority [id=%s, authority=%s]",
                id, authority
        );
    }

    @Override
    public Authority clone() {
        Authority cloneObject;
        try {
            cloneObject = (Authority) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new Authority();
        }
        cloneObject.authority = authority;

        return cloneObject;
    }

}
