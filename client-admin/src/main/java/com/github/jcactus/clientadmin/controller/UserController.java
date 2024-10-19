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

import com.github.jcactus.clientadmin.model.User;
import com.github.jcactus.clientadmin.model.UserState;
import com.github.jcactus.clientadmin.services.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/signup")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "admin-panel/users/userCreate";
    }

    @PostMapping("/signup")
    public String create(@ModelAttribute("user") User user) {
        user.setState(UserState.Enable);
        service.save(user);
        return "redirect:/oauth2/authorization/web-client-oidc";
    }

    @GetMapping
    public String getAll(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model) {
        List<User> result = service.getAll(auth);
        model.addAttribute("users", result);
        return "admin-panel/users/userList";
    }

    @GetMapping("/{id}")
    public String getById(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id) {
        User result = service.getById(auth, id);
        model.addAttribute("user", result);
        return "admin-panel/users/userEdit";
    }

    @PutMapping("/{id}")
    public String updateObject(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id,
            @ModelAttribute("user") User user) {
        user.setId(id);
        User result = service.updateObject(auth, user);
        return "redirect:/users/" + result.getId();
    }

    @PatchMapping("/{id}")
    public String updateParameters(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth,
            Model model,
            @PathVariable("id") Long id,
            @ModelAttribute("user") User user) {
        user.setId(id);
        User result = service.updateParameters(auth, user);
        return "redirect:/users/" + result.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteById(@RegisteredOAuth2AuthorizedClient("web-client-oidc") OAuth2AuthorizedClient auth, @PathVariable("id") Long id) {
        service.deleteById(auth, id);
        return "redirect:/users";
    }

}
