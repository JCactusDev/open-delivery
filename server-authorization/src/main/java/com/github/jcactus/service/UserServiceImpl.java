package com.github.jcactus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.jcactus.model.User;

@Service
public class UserServiceImpl implements UserService {

    private final WebClient webClient;

    private final DiscoveryClient discoveryClient;

    @Autowired
    public UserServiceImpl(WebClient webClient, DiscoveryClient discoveryClient) {
        this.webClient = webClient;
        this.discoveryClient = discoveryClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ServiceInstance serviceInstance = discoveryClient.getInstances("server-userdata").get(0);
        return webClient.get()
                .uri(serviceInstance.getUri() + "/api/v1/users/getByUsername/" + username)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> response.bodyToMono(String.class).map(UsernameNotFoundException::new))
                .bodyToMono(User.class)
                .block();
    }

}