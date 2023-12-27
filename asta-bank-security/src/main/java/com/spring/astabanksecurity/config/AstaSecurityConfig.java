package com.spring.astabanksecurity.config;

import com.spring.astabanksecurity.filter.AuthoritiesLoggingAfterFilter;
import com.spring.astabanksecurity.filter.AuthoritiesLoggingAtFilter;
import com.spring.astabanksecurity.filter.CsrfCookieFilter;
import com.spring.astabanksecurity.filter.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity(debug = true) // not recommended for production code
@Configuration
public class AstaSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http
                .securityContext(securityConfigurer -> securityConfigurer.requireExplicitSave(false))
                .sessionManagement(sessrionManagement -> sessrionManagement.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setMaxAge(3600L);
                    return config;
                }))
                .csrf(csrfConfigurer -> csrfConfigurer
                        .csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers("/contact", "/signup")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/accounts").hasAuthority("VIEWACCOUNT")
//                        .requestMatchers("/balance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
//                        .requestMatchers("/loan").hasAuthority("VIEWLOANS")
//                        .requestMatchers("/card").hasAuthority("VIEWCARDS")
                        .requestMatchers("/accounts").hasRole("USER")
                        .requestMatchers("/balance").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/loan").hasRole("USER")
                        .requestMatchers("/card").hasRole("USER")
                        .requestMatchers("/user").authenticated()
                        .requestMatchers("/notices", "/contact", "/signup").permitAll()
                )
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
