package com.github.jcactus.clientadmin.services;

import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import com.github.jcactus.clientadmin.model.Product;

public interface ProductService {

    Product save(OAuth2AuthorizedClient auth, Product product);

    List<Product> getAll(OAuth2AuthorizedClient auth);

    Product getById(OAuth2AuthorizedClient auth, Long id);

    Product updateObject(OAuth2AuthorizedClient auth, Long id, Product product);

    Product updateParameters(OAuth2AuthorizedClient auth, Long id, Product product);

    void deleteById(OAuth2AuthorizedClient auth, Long id);

}
