package com.github.jcactus.clientadmin.services;

import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import com.github.jcactus.clientadmin.model.User;

public interface UserService {

    User save(User user);

    List<User> getAll(OAuth2AuthorizedClient auth);

    User getById(OAuth2AuthorizedClient auth, Long id);

    User updateObject(OAuth2AuthorizedClient auth, User user);

    User updateParameters(OAuth2AuthorizedClient auth, User user);

    void deleteById(OAuth2AuthorizedClient auth, Long id);

}
