package com.authentication.simplejwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        if ("username".equals(username)) {
            return new User("username", "$2y$12$fHIu2/kiwfGC.PRagTl.8evw/2cklnBlTfGEzaf8HLPu2HBBxCH0u", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
