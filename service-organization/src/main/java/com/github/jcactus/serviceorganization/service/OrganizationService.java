package com.github.jcactus.serviceorganization.service;

import com.github.jcactus.serviceorganization.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {

    OrganizationDto save(OrganizationDto dto);

    List<OrganizationDto> findAll();

    OrganizationDto findById(long id);

    Long count();

    OrganizationDto updateObjectById(Long id, OrganizationDto dto);

    OrganizationDto updateParametersById(Long id, OrganizationDto dto);

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}
