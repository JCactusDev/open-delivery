package com.github.jcactus.repository;

import com.github.jcactus.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}
