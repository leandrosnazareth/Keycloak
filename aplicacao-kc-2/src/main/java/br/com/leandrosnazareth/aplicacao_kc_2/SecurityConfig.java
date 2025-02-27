package br.com.leandrosnazareth.aplicacao_kc_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
            ClientRegistrationRepository clientRegistrationRepository) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/secure/**").authenticated() // Protege as rotas "/" e "/secure"
                // .anyRequest().permitAll() // Permite o restante sem autenticação
                )
                .oauth2Login(Customizer.withDefaults()) // Login via OAuth2 (Keycloak)
                .logout(logout -> logout
                        .logoutSuccessHandler(oidcLogoutHandler(clientRegistrationRepository)) // Garante logout correto
                                                                                               // no Keycloak
                        .logoutUrl("/logout") // Define a URL para logout
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .csrf(AbstractHttpConfigurer::disable); // Desabilita CSRF se for necessário

        return http.build();
    }

    @Bean
    public OidcClientInitiatedLogoutSuccessHandler oidcLogoutHandler(
            ClientRegistrationRepository clientRegistrationRepository) {
        OidcClientInitiatedLogoutSuccessHandler logoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(
                clientRegistrationRepository);
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8083/"); // Define para onde o usuário será
                                                                                 // redirecionado após o logout
        return logoutSuccessHandler;
    }
}