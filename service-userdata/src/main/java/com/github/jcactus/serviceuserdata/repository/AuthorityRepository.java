package com.github.jcactus.serviceuserdata.repository;

import com.github.jcactus.serviceuserdata.model.Authority;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Optional<Authority> findByAuthority(String authority);
}
