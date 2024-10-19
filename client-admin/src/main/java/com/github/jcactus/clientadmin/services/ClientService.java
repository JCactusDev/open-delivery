package com.github.jcactus.clientadmin.services;

import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import com.github.jcactus.clientadmin.model.Client;

public interface ClientService {

    Client save(OAuth2AuthorizedClient auth, Client client);

    List<Client> getAll(OAuth2AuthorizedClient auth);

    Client getById(OAuth2AuthorizedClient auth, Long id);

    Client updateObject(OAuth2AuthorizedClient auth, Client client);

    Client updateParameters(OAuth2AuthorizedClient auth, Client client);

    void deleteById(OAuth2AuthorizedClient auth, Long id);

}
