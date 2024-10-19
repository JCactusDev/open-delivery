package com.github.jcactus.clientadmin.services;

import com.github.jcactus.clientadmin.model.Client;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Service
public class ClientServiceImpl implements ClientService {

    private final WebClient webClient;

    public ClientServiceImpl(WebClient defaultWebClient) {
        this.webClient = defaultWebClient;
    }

    @Override
    public Client save(OAuth2AuthorizedClient auth,
            Client client) {
        return webClient.post()
                .uri("http://127.0.0.1:8080/api/v1/clients")
                .attributes(oauth2AuthorizedClient(auth))
                .bodyValue(client)
                .retrieve()
                .bodyToMono(Client.class)
                .block();
    }

    @Override
    public List<Client> getAll(OAuth2AuthorizedClient auth) {
        Client[] result = webClient.get()
                .uri("http://127.0.0.1:8080/api/v1/clients")
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(Client[].class)
                .block();
        if (result == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(result);
    }

    @Override
    public Client getById(OAuth2AuthorizedClient auth, Long id) {
        return webClient.get()
                .uri("http://127.0.0.1:8080/api/v1/clients/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(Client.class)
                .block();
    }

    @Override
    public Client updateObject(OAuth2AuthorizedClient auth, Client client) {
        return webClient.put()
                .uri("http://127.0.0.1:8080/api/v1/clients")
                .attributes(oauth2AuthorizedClient(auth))
                .bodyValue(client)
                .retrieve()
                .bodyToMono(Client.class)
                .block();
    }

    @Override
    public Client updateParameters(OAuth2AuthorizedClient auth, Client client) {
        return webClient.patch()
                .uri("http://127.0.0.1:8080/api/v1/clients")
                .attributes(oauth2AuthorizedClient(auth))
                .bodyValue(client)
                .retrieve()
                .bodyToMono(Client.class)
                .block();
    }

    @Override
    public void deleteById(OAuth2AuthorizedClient auth, Long id) {
        Mono<Void> mono = webClient.delete()
                .uri("http://127.0.0.1:8080/api/v1/clients/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(Void.class);
        mono.block();
    }

}
