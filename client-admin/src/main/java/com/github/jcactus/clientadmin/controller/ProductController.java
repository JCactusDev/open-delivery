package com.github.jcactus.clientadmin.controller;

import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.jcactus.clientadmin.model.Product;
import com.github.jcactus.clientadmin.services.ProductServiceImpl;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl service;

    public ProductController(ProductServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model) {
        model.addAttribute("product", new Product());
        return "admin-panel/products/productCreate";
    }

    @PostMapping("/create")
    public String create(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @ModelAttribute("product") Product product) {
        Product result = service.save(auth, product);
        return "redirect:/products/" + result.getId();
    }

    @GetMapping
    public String getAll(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model) {
        List<Product> result = service.getAll(auth);
        model.addAttribute("products", result);
        return "admin-panel/products/productList";
    }

    @GetMapping("/{id}")
    public String getById(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id) {
        Product result = service.getById(auth, id);
        model.addAttribute("product", result);
        return "admin-panel/products/productEdit";
    }

    @PutMapping("/{id}")
    public String updateObject(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id,
            @ModelAttribute("client") Product product) {
        product.setId(id);
        Product result = service.updateObject(auth, product);
        return "redirect:/products/" + result.getId();
    }

    @PatchMapping("/{id}")
    public String updateParameters(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id,
            @ModelAttribute("product") Product product) {
        product.setId(id);
        Product result = service.updateParameters(auth, product);
        return "redirect:/products/" + result.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteById(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth, @PathVariable("id") Long id) {
        service.deleteById(auth, id);
        return "redirect:/products";
    }

}
