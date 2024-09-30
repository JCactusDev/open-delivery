package com.github.jcactus.services;

import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import com.github.jcactus.model.Organization;

public interface OrganizationService {

    Organization save(OAuth2AuthorizedClient auth, Organization organization);

    List<Organization> getAll(OAuth2AuthorizedClient auth);

    Organization getById(OAuth2AuthorizedClient auth, Long id);

    Organization updateObjectById(OAuth2AuthorizedClient auth, Long id, Organization organization);

    Organization updateParametersById(OAuth2AuthorizedClient auth, Long id, Organization organization);

    void deleteById(OAuth2AuthorizedClient auth, Long id);

}
