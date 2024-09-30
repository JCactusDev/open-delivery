package com.github.jcactus.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.github.jcactus.model.Organization;
import com.github.jcactus.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationServiceImpl(OrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Organization save(Organization organization) {
        return repository.save(organization);
    }

    @Override
    public List<Organization> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).sorted(Comparator.comparing(Organization::getId)).collect(Collectors.toList());
    }

    @Override
    public Organization findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public Organization updateObjectById(Long id, Organization organization) {
        Organization updatedOrganization = repository.findById(id).orElse(null);
        if (updatedOrganization == null) {
            return null;
        }
        updatedOrganization = organization.clone();
        updatedOrganization.setId(id);
        return repository.save(updatedOrganization);
    }

    @Override
    public Organization updateParametersById(Long id, Organization organization) {
        Organization updatedOrganization = repository.findById(id).orElse(null);
        if (updatedOrganization == null) {
            return null;
        }
        if (organization.getName() != null) {
            updatedOrganization.setName(organization.getName());
        }
        if (organization.getFullName() != null) {
            updatedOrganization.setFullName(organization.getFullName());
        }
        if (organization.getShortName() != null) {
            updatedOrganization.setShortName(organization.getShortName());
        }
        if (organization.getInternationalName() != null) {
            updatedOrganization.setInternationalName(organization.getInternationalName());
        }
        if (organization.getType() != null) {
            updatedOrganization.setType(organization.getType());
        }
        if (organization.getTaxNumber() != null) {
            updatedOrganization.setTaxNumber(organization.getTaxNumber());
        }
        if (organization.getRegNumber() != null) {
            updatedOrganization.setRegNumber(organization.getRegNumber());
        }
        if (organization.getRegDate() != null) {
            updatedOrganization.setRegDate(organization.getRegDate());
        }
        if (organization.getDescription() != null) {
            updatedOrganization.setDescription(organization.getDescription());
        }
        return repository.save(updatedOrganization);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long id) {
        return repository.existsByNameAndIdNot(name, id);
    }

}
