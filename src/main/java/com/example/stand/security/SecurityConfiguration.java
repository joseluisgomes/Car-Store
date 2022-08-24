package com.example.stand.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static com.example.stand.employee.office.Role.ADMINISTRATOR;
import static com.example.stand.employee.office.Role.SECRETARY;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> {
                    authorize.antMatchers("/", "/login", "/css/*", "/js/*")
                            .permitAll();
                    authorize.antMatchers("/employee/**", "/vehicle/**", "/data", "/index")
                            .hasAnyRole(ADMINISTRATOR.getRole(), SECRETARY.getRole());
                })
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/data", true)
                .and()
                .logout()
                    .logoutSuccessUrl("/login");
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
}
