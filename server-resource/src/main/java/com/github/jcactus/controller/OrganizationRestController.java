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

import com.github.jcactus.model.Organization;
import com.github.jcactus.service.OrganizationServiceImpl;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationRestController {

    private final OrganizationServiceImpl service;

    public OrganizationRestController(OrganizationServiceImpl service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody Organization organization) {
        if (organization == null
                || organization.getId() != null
                || organization.getName() == null
                || organization.getFullName() == null
                || organization.getShortName() == null
                || organization.getType() == null
                || organization.getRegNumber() == null
                || organization.getRegDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (service.existsByName(organization.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Organization result = service.save(organization);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Object> getAll() {
        List<Organization> result = service.findAll();
        return result.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Organization result = service.findById(id);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateObjectById(@PathVariable Long id, @RequestBody Organization organization) {
        if (id == null
                || organization == null
                || organization.getName() == null
                || organization.getFullName() == null
                || organization.getShortName() == null
                || organization.getType() == null
                || organization.getRegNumber() == null
                || organization.getRegDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (service.existsByNameAndIdNot(organization.getName(), organization.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Organization result = service.updateObjectById(id, organization);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateParametersById(@PathVariable Long id, @RequestBody Organization organization) {
        if (id == null
                || organization == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (service.existsByNameAndIdNot(organization.getName(), organization.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Organization result = service.updateParametersById(id, organization);
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
