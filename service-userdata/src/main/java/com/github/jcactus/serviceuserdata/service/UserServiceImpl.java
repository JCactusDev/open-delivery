package com.github.jcactus.serviceuserdata.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.github.jcactus.serviceuserdata.model.Authority;
import com.github.jcactus.serviceuserdata.model.User;
import com.github.jcactus.serviceuserdata.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    @Transactional
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByUsernameAndIdNot(String username, Long id) {
        return userRepository.existsByUsernameAndIdNot(username, id);
    }

    @Override
    @Transactional
    public Long count() {
        return userRepository.count();
    }

    @Override
    @Transactional
    public User updateObjectById(Long id, User user) {
        User updateUser = userRepository.findById(id).orElse(null);
        if (updateUser == null) {
            return null;
        }
        updateUser = user.clone();
        updateUser.setId(id);
        return userRepository.save(updateUser);
    }

    @Override
    @Transactional
    public User updateParametersById(Long id, User user) {
        User updateUser = userRepository.findById(id).orElse(null);
        if (updateUser == null) {
            return null;
        }
        if (user.getUsername() != null) {
            updateUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            updateUser.setPassword(user.getPassword());
        }
        if (user.getAuthorities() != null) {
            updateUser.getAuthorities().clear();
            for (Authority authority : user.getAuthorities()) {
                updateUser.getAuthorities().add(authority);
            }
        }
        if (user.getState() != null) {
            updateUser.setState(user.getState());
        }
        if (user.getEmail() != null) {
            updateUser.setEmail(user.getEmail());
        }
        if (user.getPhone() != null) {
            updateUser.setPhone(user.getPhone());
        }
        return userRepository.save(updateUser);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
