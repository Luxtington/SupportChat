package ru.luxtington.Chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/api/lu/**").permitAll()
                .requestMatchers("/lu/auth/**", "/auth/**").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/users/*/change-role").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/users/*/delete").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/users/*/edit").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/users/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/chats/*/create").hasAnyAuthority("ROLE_USER")
                .requestMatchers("/chats/*/delete").hasAnyAuthority("ROLE_USER")
                .requestMatchers("/chats/*/send").hasAuthority("ROLE_USER, ROLE_MANAGER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/lu/auth/login")
                .loginProcessingUrl("/lu/auth/login")
                .defaultSuccessUrl("/chats", true)
                .failureUrl("/lu/auth/login?error=true")
                .and()
                .logout()
                .logoutUrl("/lu/auth/logout")
                .logoutSuccessUrl("/lu/auth/login")
                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
