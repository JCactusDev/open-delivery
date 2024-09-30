package com.github.jcactus.services;

import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import com.github.jcactus.model.Product;

public interface ProductService {

    Product save(OAuth2AuthorizedClient auth, Product product);

    List<Product> getAll(OAuth2AuthorizedClient auth);

    Product getById(OAuth2AuthorizedClient auth, Long id);

    Product updateObjectById(OAuth2AuthorizedClient auth, Long id, Product product);

    Product updateParametersById(OAuth2AuthorizedClient auth, Long id, Product product);

    void deleteById(OAuth2AuthorizedClient auth, Long id);

}
