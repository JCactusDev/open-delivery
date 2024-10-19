package com.github.jcactus.serviceorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/orders").hasAuthority("SCOPE_orders.update")
                        .requestMatchers(HttpMethod.GET, "/api/v1/orders").hasAuthority("SCOPE_orders.read")
                        .requestMatchers(HttpMethod.GET, "/api/v1/orders/{id}").hasAuthority("SCOPE_orders.read")
                        .requestMatchers(HttpMethod.GET, "/api/v1/orders/existsById/{id}").hasAuthority("SCOPE_orders.read")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/orders").hasAuthority("SCOPE_orders.update")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/orders").hasAuthority("SCOPE_orders.update")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/orders/{id}").hasAuthority("SCOPE_orders.delete")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }
    
}
