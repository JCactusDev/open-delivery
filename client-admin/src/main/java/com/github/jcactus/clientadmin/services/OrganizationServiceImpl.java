package com.github.jcactus.clientadmin.services;

import com.github.jcactus.clientadmin.model.Organization;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final WebClient webClient;

    public OrganizationServiceImpl(WebClient defaultWebClient) {
        this.webClient = defaultWebClient;
    }

    @Override
    public Organization save(OAuth2AuthorizedClient auth, Organization organization) {
        return webClient.post()
                .uri("http://127.0.0.1:8080/api/v1/organizations")
                .attributes(oauth2AuthorizedClient(auth))
                .bodyValue(organization)
                .retrieve()
                .bodyToMono(Organization.class)
                .block();
    }

    @Override
    public List<Organization> getAll(OAuth2AuthorizedClient auth) {
        Organization[]result = webClient.get()
                .uri("http://127.0.0.1:8080/api/v1/organizations")
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(Organization[].class)
                .block();
        if (result == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(result);
    }

    @Override
    public Organization getById(OAuth2AuthorizedClient auth, Long id) {
        return webClient.get()
                .uri("http://127.0.0.1:8080/api/v1/organizations/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(Organization.class)
                .block();
    }

    @Override
    public Organization updateObject(OAuth2AuthorizedClient auth, Long id, Organization organization) {
        return webClient.put()
                .uri("http://127.0.0.1:8080/api/v1/organizations/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .bodyValue(organization)
                .retrieve()
                .bodyToMono(Organization.class)
                .block();
    }

    @Override
    public Organization updateParameters(OAuth2AuthorizedClient auth, Long id, Organization organization) {
        return webClient.patch()
                .uri("http://127.0.0.1:8080/api/v1/organizations/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .bodyValue(organization)
                .retrieve()
                .bodyToMono(Organization.class)
                .block();
    }

    @Override
    public void deleteById(OAuth2AuthorizedClient auth, Long id) {
        Mono<Void> mono = webClient.delete()
                .uri("http://127.0.0.1:8080/api/v1/organizations/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(Void.class);
        mono.block();
    }

}
