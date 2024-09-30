package com.github.jcactus.controller;

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

import com.github.jcactus.model.Organization;
import com.github.jcactus.services.OrganizationServiceImpl;

@Controller
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationServiceImpl service;

    public OrganizationController(OrganizationServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model) {
        model.addAttribute("organization", new Organization());
        return "admin-panel/organizations/organizationCreate";
    }

    @PostMapping("/create")
    public String create(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @ModelAttribute("organization") Organization organization) {
        Organization result = service.save(auth, organization);
        return "redirect:/organizations/" + result.getId();
    }

    @GetMapping
    public String getAll(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model) {
        List<Organization> result = service.getAll(auth);
        model.addAttribute("organizations", result);
        return "admin-panel/organizations/organizationList";
    }

    @GetMapping("/{id}")
    public String getById(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id) {
        Organization result = service.getById(auth, id);
        model.addAttribute("organization", result);
        return "admin-panel/organizations/organizationEdit";
    }

    @PutMapping("/{id}")
    public String updateObjectById(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id,
            @ModelAttribute("organization") Organization organization) {
        organization.setId(id);
        Organization result = service.updateObjectById(auth, id, organization);
        model.addAttribute("organization", result);
        return "admin-panel/organizations/organizationEdit";
    }

    @PatchMapping("/{id}")
    public String updateParametersById(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id,
            @ModelAttribute("organization") Organization organization) {
        organization.setId(id);
        Organization result = service.updateParametersById(auth, id, organization);
        model.addAttribute("organization", result);
        return "admin-panel/organizations/organizationEdit";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth, @PathVariable("id") Long id) {
        service.deleteById(auth, id);
        return "redirect:/organizations";
    }

}
