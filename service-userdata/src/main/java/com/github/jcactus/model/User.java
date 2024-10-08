package com.github.jcactus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name ="password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @NotNull
    @Column(name = "state")
    private UserState state;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Override
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
        authorities.remove(authority);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonLocked() {
        return state == UserState.Enable;
    }

    @Override
    public boolean isEnabled() {
        return state == UserState.Enable;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
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
        User other = (User) otherObject;
        // Проверка хранимых значений в свойствах объекта
        return Objects.equals(id, other.id)
                && Objects.equals(username, other.username)
                && Objects.equals(password, other.password)
                && Objects.equals(authorities, other.authorities)
                && Objects.equals(state, other.state)
                && Objects.equals(email, other.email)
                && Objects.equals(phone, other.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, authorities, state, email, phone);
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, username=%s, password=%s, authorities=%s, state=%s, email=%s, phone=%s]",
                id, username, password, authorities, state, email, phone
        );
    }

    @Override
    public User clone() {
        User cloneObject;
        try {
            cloneObject = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            cloneObject = new User();
        }
        cloneObject.username = username;
        cloneObject.password = password;
        cloneObject.authorities = new HashSet<>();
        cloneObject.authorities.addAll(authorities);
        cloneObject.state = state;
        cloneObject.email = email;
        cloneObject.phone = phone;
        return cloneObject;
    }
}
