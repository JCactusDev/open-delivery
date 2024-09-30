package com.github.jcactus.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.github.jcactus.model.Client;
import com.github.jcactus.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).sorted(Comparator.comparing(Client::getId)).collect(Collectors.toList());
    }

    @Override
    public Client findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public Client updateObjectById(Long id, Client client) {
        Client updatedClient = repository.findById(id).orElse(null);
        if (updatedClient == null) {
            return null;
        }
        updatedClient = client.clone();
        updatedClient.setId(id);
        return repository.save(updatedClient);
    }

    @Override
    public Client updateParametersById(Long id, Client client) {
        Client updatedClient = repository.findById(id).orElse(null);
        if (updatedClient == null) {
            return null;
        }
        if (client.getName() != null) {
            updatedClient.setName(client.getName());
        }
        if (client.getFullName() != null) {
            updatedClient.setFullName(client.getFullName());
        }
        if (client.getShortName() != null) {
            updatedClient.setShortName(client.getShortName());
        }
        if (client.getInternationalName() != null) {
            updatedClient.setInternationalName(client.getInternationalName());
        }
        if (client.getType() != null) {
            updatedClient.setType(client.getType());
        }
        if (client.getDescription() != null) {
            updatedClient.setDescription(client.getDescription());
        }
        return repository.save(updatedClient);
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
