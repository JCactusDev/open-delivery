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

import com.github.jcactus.clientadmin.model.Client;
import com.github.jcactus.clientadmin.services.ClientServiceImpl;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientServiceImpl service;

    public ClientController(ClientServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model) {
        model.addAttribute("client", new Client());
        return "admin-panel/clients/clientCreate";
    }

    @PostMapping("/create")
    public String create(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @ModelAttribute("client") Client client) {
        Client result = service.save(auth, client);
        return "redirect:/clients/" + result.getId();
    }

    @GetMapping
    public String getAll(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model) {
        List<Client> result = service.getAll(auth);
        model.addAttribute("clients", result);
        return "admin-panel/clients/clientList";
    }

    @GetMapping("/{id}")
    public String getById(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id) {
        Client result = service.getById(auth, id);
        model.addAttribute("client", result);
        return "admin-panel/clients/clientEdit";
    }

    @PutMapping("/{id}")
    public String updateObject(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id,
            @ModelAttribute("client") Client client) {
        client.setId(id);
        Client result = service.updateObject(auth, id, client);
        return "redirect:/clients/" + result.getId();
    }

    @PatchMapping("/{id}")
    public String updateParameters(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id,
            @ModelAttribute("client") Client client) {
        client.setId(id);
        Client result = service.updateParameters(auth, id, client);
        return "redirect:/clients/" + result.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteById(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth, @PathVariable("id") Long id) {
        service.deleteById(auth, id);
        return "redirect:/clients";
    }

}
