package com.shmyrov.testtask.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.profiles.active}")
    private String profile;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        if (profile.equals("dev")) {
            return http.csrf().disable().authorizeRequests().anyRequest().permitAll().and().build();
        }
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/static/**", "/favicon.ico", "/manifect.json", "/robots.txt").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/perform_login").loginPage("/morda/login").permitAll()
                .defaultSuccessUrl("/morda/", true)
                .failureUrl("/morda/login?error=true")
                .and()
                .logout().logoutSuccessUrl("/morda/login?logout=true");
        return http.build();
    }
}
