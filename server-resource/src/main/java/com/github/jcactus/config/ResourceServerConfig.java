package com.github.jcactus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/v1/organizations").hasAuthority("SCOPE_organizations.update")
                .requestMatchers(HttpMethod.GET, "/api/v1/organizations").hasAuthority("SCOPE_organizations.read")
                .requestMatchers(HttpMethod.GET, "/api/v1/organizations/{id}").hasAuthority("SCOPE_organizations.read")
                .requestMatchers(HttpMethod.PUT, "/api/v1/organizations/{id}").hasAuthority("SCOPE_organizations.update")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/organizations/{id}").hasAuthority("SCOPE_organizations.update")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/organizations/{id}").hasAuthority("SCOPE_organizations.delete")
                //
                .requestMatchers(HttpMethod.POST, "/api/v1/clients").hasAuthority("SCOPE_clients.update")
                .requestMatchers(HttpMethod.GET, "/api/v1/clients").hasAuthority("SCOPE_clients.read")
                .requestMatchers(HttpMethod.GET, "/api/v1/clients/{id}").hasAuthority("SCOPE_clients.read")
                .requestMatchers(HttpMethod.PUT, "/api/v1/clients/{id}").hasAuthority("SCOPE_clients.update")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/clients/{id}").hasAuthority("SCOPE_clients.update")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/clients/{id}").hasAuthority("SCOPE_clients.delete")
                //
                .requestMatchers(HttpMethod.POST, "/api/v1/products").hasAuthority("SCOPE_products.update")
                .requestMatchers(HttpMethod.GET, "/api/v1/products").hasAuthority("SCOPE_products.read")
                .requestMatchers(HttpMethod.GET, "/api/v1/products/{id}").hasAuthority("SCOPE_products.read")
                .requestMatchers(HttpMethod.PUT, "/api/v1/products/{id}").hasAuthority("SCOPE_products.update")
                .requestMatchers(HttpMethod.PATCH, "/api/v1/products/{id}").hasAuthority("SCOPE_products.update")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/products/{id}").hasAuthority("SCOPE_products.delete")
                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

}
