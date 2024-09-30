package com.github.jcactus.services;

import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import com.github.jcactus.model.Client;

public interface ClientService {

    Client save(OAuth2AuthorizedClient auth, Client client);

    List<Client> getAll(OAuth2AuthorizedClient auth);

    Client getById(OAuth2AuthorizedClient auth, Long id);

    Client updateObjectById(OAuth2AuthorizedClient auth, Long id, Client client);

    Client updateParametersById(OAuth2AuthorizedClient auth, Long id, Client client);

    void deleteById(OAuth2AuthorizedClient auth, Long id);

}
