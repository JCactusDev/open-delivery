package com.github.jcactus.serviceclient.controller;

import com.github.jcactus.serviceclient.dto.ClientDto;
import com.github.jcactus.serviceclient.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientRestController {

    private final ClientService service;

    public ClientRestController(ClientService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody ClientDto client) {
        if (client == null
                || client.getId() != null
                || client.getName() == null
                || client.getFullName() == null
                || client.getShortName() == null
                || client.getType() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (service.existsByName(client.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        ClientDto result = service.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Object> getAll() {
        List<ClientDto> result = service.findAll();
        return result.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ClientDto result = service.findById(id);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/existsById/{id}", produces = "application/json")
    public ResponseEntity<Object> existsById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return !service.existsById(id) ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(false) : ResponseEntity.status(HttpStatus.FOUND).body(true);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateObject(@RequestBody ClientDto client) {
        if (client == null
                || client.getId() == null
                || client.getName() == null
                || client.getFullName() == null
                || client.getShortName() == null
                || client.getType() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(client.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (service.existsByNameAndIdNot(client.getName(), client.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        ClientDto result = service.updateObject(client);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateParameters(@RequestBody ClientDto client) {
        if (client == null
                || client.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(client.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (service.existsByNameAndIdNot(client.getName(), client.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        ClientDto result = service.updateParameters(client);
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
