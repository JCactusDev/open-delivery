package com.github.jcactus.repository;

import com.github.jcactus.model.Authority;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Optional<Authority> findByAuthority(String authority);
}
