package com.github.jcactus.serviceuserdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").anonymous()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/getByUsername/{username}").anonymous()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users").hasAuthority("SCOPE_users.read")
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/{id}").hasAuthority("SCOPE_users.read")
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/existsById/{id}").hasAuthority("SCOPE_users.read")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/{id}").hasAuthority("SCOPE_users.update")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/users/{id}").hasAuthority("SCOPE_users.update")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/{id}").hasAuthority("SCOPE_users.delete")
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/v1/users/getByUsername/{username}")
                        .ignoringRequestMatchers("/api/v1/users")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

}