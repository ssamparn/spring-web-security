package com.spring.astabanksecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AstaSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/account", "/balance", "/card", "/loan")
                        .authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/notices", "/contact")
                        .permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    @Profile("dev")
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("asta-admin")
                .password("adminpassword")
                .authorities("admin")
                .build();

        UserDetails user = User.withUsername("asta-user")
                .password("userpassword")
                .authorities("user")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    @Profile("prod")
    public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
