package com.github.jcactus.service;

import com.github.jcactus.model.Organization;

import java.util.List;

public interface OrganizationService {

    Organization save(Organization organization);

    List<Organization> findAll();

    Organization findById(long id);

    Long count();

    Organization updateObjectById(Long id, Organization organization);

    Organization updateParametersById(Long id, Organization organization);

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}
