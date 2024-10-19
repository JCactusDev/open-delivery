package com.github.jcactus.serviceuserdata.service;

import com.github.jcactus.serviceuserdata.model.User;
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

    User updateObject(User user);

    User updateParameters(User user);

    void deleteById(long id);

    boolean existsByUsernameAndIdNot(String username, Long id);

}
