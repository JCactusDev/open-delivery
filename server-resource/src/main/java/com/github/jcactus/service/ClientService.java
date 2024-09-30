package com.github.jcactus.service;

import com.github.jcactus.model.Client;

import java.util.List;

public interface ClientService {

    Client save(Client client);

    List<Client> findAll();

    Client findById(long id);

    Long count();

    Client updateObjectById(Long id, Client client);

    Client updateParametersById(Long id, Client client);

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}
