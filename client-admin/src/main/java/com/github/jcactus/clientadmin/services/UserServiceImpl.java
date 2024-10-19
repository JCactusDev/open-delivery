package com.github.jcactus.clientadmin.services;

import com.github.jcactus.clientadmin.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Service
public class UserServiceImpl implements UserService {

    private final WebClient webClient;

    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(WebClient defaultWebClient, PasswordEncoder passwordEncoder) {
        this.webClient = defaultWebClient;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return webClient.post()
                .uri("http://127.0.0.1:8080/api/v1/users")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    @Override
    public List<User> getAll(OAuth2AuthorizedClient auth) {
        User[] result = webClient.get()
                .uri("http://127.0.0.1:8080/api/v1/users")
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(User[].class)
                .block();
        if (result == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(result);
    }

    @Override
    public User getById(OAuth2AuthorizedClient auth, Long id) {
        return webClient.get()
                .uri("http://127.0.0.1:8080/api/v1/users/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    @Override
    public User updateObject(OAuth2AuthorizedClient auth, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return webClient.put()
                .uri("http://127.0.0.1:8080/api/v1/users")
                .attributes(oauth2AuthorizedClient(auth))
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    @Override
    public User updateParameters(OAuth2AuthorizedClient auth, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return webClient.patch()
                .uri("http://127.0.0.1:8080/api/v1/users")
                .attributes(oauth2AuthorizedClient(auth))
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    @Override
    public void deleteById(OAuth2AuthorizedClient auth, Long id) {
        Mono<Void> mono = webClient.delete()
                .uri("http://127.0.0.1:8080/api/v1/users/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(Void.class);
        mono.block();
    }

}
