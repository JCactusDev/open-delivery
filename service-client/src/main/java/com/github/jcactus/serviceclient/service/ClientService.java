package com.github.jcactus.serviceclient.service;

import com.github.jcactus.serviceclient.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto client);

    List<ClientDto> findAll();

    ClientDto findById(long id);

    Long count();

    ClientDto updateObject(ClientDto client);

    ClientDto updateParameters(ClientDto client);

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}
