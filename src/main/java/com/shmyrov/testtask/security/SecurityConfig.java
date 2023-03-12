package com.shmyrov.testtask.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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
