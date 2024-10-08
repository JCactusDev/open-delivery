package com.github.jcactus.clientadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/users/signup").anonymous()
                        .requestMatchers(HttpMethod.GET, "/users").hasAnyAuthority("OIDC_USER", "SCOPE_users.read")
                        .requestMatchers(HttpMethod.GET, "/users/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_users.read")
                        .requestMatchers(HttpMethod.PUT, "/users/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_users.update")
                        .requestMatchers(HttpMethod.PATCH, "/users/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_users.update")
                        .requestMatchers(HttpMethod.DELETE, "/users/{id}").hasAnyAuthority("OIDC_ADMIN", "SCOPE_users.delete")
                        //
                        .requestMatchers(HttpMethod.POST, "/organizations/create").hasAnyAuthority("OIDC_USER", "SCOPE_organizations.update")
                        .requestMatchers(HttpMethod.GET, "/organizations/create").hasAnyAuthority("OIDC_USER", "SCOPE_organizations.update")
                        .requestMatchers(HttpMethod.GET, "/organizations").hasAnyAuthority("OIDC_USER", "SCOPE_organizations.read")
                        .requestMatchers(HttpMethod.GET, "/organizations/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_organizations.read")
                        .requestMatchers(HttpMethod.PUT, "/organizations/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_organizations.update")
                        .requestMatchers(HttpMethod.PATCH, "/organizations/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_organizations.update")
                        .requestMatchers(HttpMethod.DELETE, "/organizations/{id}").hasAnyAuthority("OIDC_ADMIN", "SCOPE_organizations.delete")
                        //
                        .requestMatchers(HttpMethod.POST, "/clients/create").hasAnyAuthority("OIDC_USER", "SCOPE_clients.update")
                        .requestMatchers(HttpMethod.GET, "/clients/create").hasAnyAuthority("OIDC_USER", "SCOPE_clients.update")
                        .requestMatchers(HttpMethod.GET, "/clients").hasAnyAuthority("OIDC_USER", "SCOPE_clients.read")
                        .requestMatchers(HttpMethod.GET, "/clients/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_clients.read")
                        .requestMatchers(HttpMethod.PUT, "/clients/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_clients.update")
                        .requestMatchers(HttpMethod.PATCH, "/clients/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_clients.update")
                        .requestMatchers(HttpMethod.DELETE, "/clients/{id}").hasAnyAuthority("OIDC_ADMIN", "SCOPE_clients.delete")
                        //
                        .requestMatchers(HttpMethod.POST, "/products/create").hasAnyAuthority("OIDC_USER", "SCOPE_products.update")
                        .requestMatchers(HttpMethod.GET, "/products/create").hasAnyAuthority("OIDC_USER", "SCOPE_products.update")
                        .requestMatchers(HttpMethod.GET, "/products").hasAnyAuthority("OIDC_USER", "SCOPE_products.read")
                        .requestMatchers(HttpMethod.GET, "/products/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_products.read")
                        .requestMatchers(HttpMethod.PUT, "/products/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_products.update")
                        .requestMatchers(HttpMethod.PATCH, "/products/{id}").hasAnyAuthority("OIDC_USER", "SCOPE_products.update")
                        .requestMatchers(HttpMethod.DELETE, "/products/{id}").hasAnyAuthority("OIDC_ADMIN", "SCOPE_products.delete")
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login")
                        .authorizationEndpoint(endpoint -> endpoint
                                .baseUri("/oauth2/authorization/web-client-oidc")
                        )
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                )
                .oauth2Client(Customizer.withDefaults())
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        final OidcClientInitiatedLogoutSuccessHandler handler =
                new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
        handler.setPostLogoutRedirectUri("{baseUrl}");
        return handler;
    }

}
