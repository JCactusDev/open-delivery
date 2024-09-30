package com.github.jcactus.service;

import com.github.jcactus.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User save(User user);

    List<User> findAll();

    User findById(long id);

    boolean existsById(long id);

    User findByUsername(String username);

    boolean existsByUsername(String username);

    Long count();

    User updateObjectById(Long id, User user);

    User updateParametersById(Long id, User user);

    void deleteById(long id);

    boolean existsByUsernameAndIdNot(String username, Long id);

}
