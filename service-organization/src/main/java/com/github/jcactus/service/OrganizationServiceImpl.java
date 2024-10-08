package com.github.jcactus.service;

import com.github.jcactus.dto.OrganizationDto;
import com.github.jcactus.mapper.OrganizationMapper;
import com.github.jcactus.model.Organization;
import com.github.jcactus.model.OrganizationType;
import com.github.jcactus.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationServiceImpl(OrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrganizationDto save(OrganizationDto organization) {
        return OrganizationMapper.toDto(repository.save(OrganizationMapper.toModel(organization)));
    }

    @Override
    public List<OrganizationDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(OrganizationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDto findById(long id) {
        return OrganizationMapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public OrganizationDto updateObjectById(Long id, OrganizationDto dto) {
        Organization model = repository.findById(id).orElse(null);
        if (model == null) {
            return null;
        }
        model.setId(id);
        model.setName(dto.getName());
        model.setFullName(dto.getFullName());
        model.setShortName(dto.getShortName());
        model.setInternationalName(dto.getInternationalName());
        model.setType(Enum.valueOf(OrganizationType.class, dto.getType()));
        model.setTaxNumber(dto.getTaxNumber());
        model.setRegNumber(dto.getRegNumber());
        model.setRegDate(dto.getRegDate());
        model.setDescription(dto.getDescription());
        return OrganizationMapper.toDto(repository.save(model));
    }

    @Override
    public OrganizationDto updateParametersById(Long id, OrganizationDto organization) {
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
            updatedOrganization.setType(Enum.valueOf(OrganizationType.class, organization.getType()));
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
        return OrganizationMapper.toDto(repository.save(updatedOrganization));
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
