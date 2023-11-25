package com.quyvx.main_server.infrastructure.config.security;

import com.quyvx.main_server.api.application.services.token.TokenService;
import com.quyvx.main_server.shared.exceptions.ExceptionHandlerFilter;
import com.quyvx.main_server.shared.libs.infrastructure.services.SharedTokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final SharedTokenService sharedTokenService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception {
        http.csrf()
                .disable()
                .cors().and()
                .authorizeHttpRequests()
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(new AuthorizationFilter(sharedTokenService), BasicAuthenticationFilter.class)
                .addFilterBefore(new ExceptionHandlerFilter(), BasicAuthenticationFilter.class)
                .logout();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)  throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
