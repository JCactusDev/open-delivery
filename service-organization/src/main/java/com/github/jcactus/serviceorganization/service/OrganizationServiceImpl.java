package com.github.jcactus.serviceorganization.service;

import com.github.jcactus.serviceorganization.dto.OrganizationDto;
import com.github.jcactus.serviceorganization.mapper.OrganizationMapper;
import com.github.jcactus.serviceorganization.model.Organization;
import com.github.jcactus.serviceorganization.model.OrganizationType;
import com.github.jcactus.serviceorganization.repository.OrganizationRepository;
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
    public OrganizationDto updateParametersById(Long id, OrganizationDto dto) {
        Organization model = repository.findById(id).orElse(null);
        if (model == null) {
            return null;
        }
        if (dto.getName() != null) {
            model.setName(dto.getName());
        }
        if (dto.getFullName() != null) {
            model.setFullName(dto.getFullName());
        }
        if (dto.getShortName() != null) {
            model.setShortName(dto.getShortName());
        }
        if (dto.getInternationalName() != null) {
            model.setInternationalName(dto.getInternationalName());
        }
        if (dto.getType() != null) {
            model.setType(Enum.valueOf(OrganizationType.class, dto.getType()));
        }
        if (dto.getTaxNumber() != null) {
            model.setTaxNumber(dto.getTaxNumber());
        }
        if (dto.getRegNumber() != null) {
            model.setRegNumber(dto.getRegNumber());
        }
        if (dto.getRegDate() != null) {
            model.setRegDate(dto.getRegDate());
        }
        if (dto.getDescription() != null) {
            model.setDescription(dto.getDescription());
        }
        return OrganizationMapper.toDto(repository.save(model));
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
