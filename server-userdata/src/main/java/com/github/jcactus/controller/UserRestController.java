package com.github.jcactus.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jcactus.model.User;
import com.github.jcactus.model.UserState;
import com.github.jcactus.service.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserServiceImpl service;

    public UserRestController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody User user) {
        if (user == null
                || user.getId() != null
                || user.getUsername() == null
                || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (service.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        user.setState(UserState.Enable);
        User result = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Object> getAll() {
        List<User> result = service.findAll();
        return result.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/getByUsername/{username}", produces = "application/json")
    public ResponseEntity<Object> getByUsername(@PathVariable String username) {
        if (username == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        User result = service.findByUsername(username);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("User with username - '%s' not found", username)) : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        User result = service.findById(id);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateObjectById(@PathVariable Long id, @RequestBody User user) {
        if (id == null
                || user == null
                || user.getUsername() == null
                || user.getPassword() == null
                || user.getAuthorities() == null
                || user.getState() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (service.existsByUsernameAndIdNot(user.getUsername(), user.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User result = service.updateObjectById(id, user);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateParametersById(@PathVariable Long id, @RequestBody User user) {
        if (id == null
                || user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (service.existsByUsernameAndIdNot(user.getUsername(), user.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User result = service.updateParametersById(id, user);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
