package com.github.jcactus.serviceclient.service;

import com.github.jcactus.serviceclient.dto.ClientDto;
import com.github.jcactus.serviceclient.mapper.ClientMapper;
import com.github.jcactus.serviceclient.model.Client;
import com.github.jcactus.serviceclient.model.OrganizationType;
import com.github.jcactus.serviceclient.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientDto save(ClientDto client) {
        return ClientMapper.toDto(repository.save(ClientMapper.toModel(client)));
    }

    @Override
    public List<ClientDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(ClientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto findById(long id) {
        return ClientMapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public ClientDto updateObject(ClientDto dto) {
        Client model = repository.findById(dto.getId()).orElse(null);
        if (model == null) {
            return null;
        }
        model.setName(dto.getName());
        model.setFullName(dto.getFullName());
        model.setShortName(dto.getShortName());
        model.setInternationalName(dto.getInternationalName());
        model.setType(Enum.valueOf(OrganizationType.class, dto.getType()));
        model.setDescription(dto.getDescription());
        return ClientMapper.toDto(repository.save(model));
    }

    @Override
    public ClientDto updateParameters(ClientDto dto) {
        Client model = repository.findById(dto.getId()).orElse(null);
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
        if (dto.getDescription() != null) {
            model.setDescription(dto.getDescription());
        }
        return ClientMapper.toDto(repository.save(model));
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
