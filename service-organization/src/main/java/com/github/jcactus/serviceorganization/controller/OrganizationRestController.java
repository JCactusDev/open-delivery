package com.github.jcactus.serviceorganization.controller;

import com.github.jcactus.serviceorganization.dto.OrganizationDto;
import com.github.jcactus.serviceorganization.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationRestController {

    private final OrganizationService service;

    public OrganizationRestController(OrganizationService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody OrganizationDto organization) {
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
        OrganizationDto result = service.save(organization);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Object> getAll() {
        List<OrganizationDto> result = service.findAll();
        return result.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        OrganizationDto result = service.findById(id);
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
    public ResponseEntity<Object> updateObject(@RequestBody OrganizationDto organization) {
        if (organization == null
                || organization.getId() == null
                || organization.getName() == null
                || organization.getFullName() == null
                || organization.getShortName() == null
                || organization.getType() == null
                || organization.getRegNumber() == null
                || organization.getRegDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(organization.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (service.existsByNameAndIdNot(organization.getName(), organization.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        OrganizationDto result = service.updateObject(organization);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateParameters(@RequestBody OrganizationDto organization) {
        if (organization == null
                || organization.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (!service.existsById(organization.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (service.existsByNameAndIdNot(organization.getName(), organization.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        OrganizationDto result = service.updateParameters(organization);
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
