package com.shmyrov.testtask.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.nio.file.attribute.UserPrincipal;

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

    @Bean
    public UserDetailsService users() {
        UserDetails user1 = User.builder()
                .username("user")
                .password("$2a$12$wNbcunPECnF9D7s6ZxlHbu5dCIpAMk.ysxia4KnGGGT78SsAkJrDW")
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("user2")
                .password("$2a$12$QJlydAhteFlnaO6Gv2ntwev5qwERrlQQMWQpBw1AOdJSgX8SWxxXy")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
