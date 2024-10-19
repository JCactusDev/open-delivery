package com.github.jcactus.clientadmin.services;

import com.github.jcactus.clientadmin.model.Product;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Service
public class ProductServiceImpl implements ProductService {

    private final WebClient webClient;

    public ProductServiceImpl(WebClient defaultWebClient) {
        this.webClient = defaultWebClient;
    }

    @Override
    public Product save(OAuth2AuthorizedClient auth,
            Product product) {
        return webClient.post()
                .uri("http://127.0.0.1:8080/api/v1/products")
                .attributes(oauth2AuthorizedClient(auth))
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    @Override
    public List<Product> getAll(OAuth2AuthorizedClient auth) {
        Product[] result = webClient.get()
                .uri("http://127.0.0.1:8080/api/v1/products")
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(Product[].class)
                .block();
        if (result == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(result);
    }

    @Override
    public Product getById(OAuth2AuthorizedClient auth, Long id) {
        return webClient.get()
                .uri("http://127.0.0.1:8080/api/v1/products/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    @Override
    public Product updateObject(OAuth2AuthorizedClient auth, Long id, Product product) {
        return webClient.put()
                .uri("http://127.0.0.1:8080/api/v1/products/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    @Override
    public Product updateParameters(OAuth2AuthorizedClient auth, Long id, Product product) {
        return webClient.patch()
                .uri("http://127.0.0.1:8080/api/v1/products/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    @Override
    public void deleteById(OAuth2AuthorizedClient auth, Long id) {
        Mono<Void> mono = webClient.delete()
                .uri("http://127.0.0.1:8080/api/v1/products/" + id)
                .attributes(oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToMono(Void.class);
        mono.block();
    }

}
