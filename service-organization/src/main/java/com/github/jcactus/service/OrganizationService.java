package com.github.jcactus.service;

import com.github.jcactus.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {

    OrganizationDto save(OrganizationDto organization);

    List<OrganizationDto> findAll();

    OrganizationDto findById(long id);

    Long count();

    OrganizationDto updateObjectById(Long id, OrganizationDto organization);

    OrganizationDto updateParametersById(Long id, OrganizationDto organization);

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}
