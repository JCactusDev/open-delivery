package com.github.jcactus.clientadmin.services;

import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import com.github.jcactus.clientadmin.model.Organization;

public interface OrganizationService {

    Organization save(OAuth2AuthorizedClient auth, Organization organization);

    List<Organization> getAll(OAuth2AuthorizedClient auth);

    Organization getById(OAuth2AuthorizedClient auth, Long id);

    Organization updateObject(OAuth2AuthorizedClient auth, Organization organization);

    Organization updateParameters(OAuth2AuthorizedClient auth, Organization organization);

    void deleteById(OAuth2AuthorizedClient auth, Long id);

}
