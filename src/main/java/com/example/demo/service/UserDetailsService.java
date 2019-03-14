package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by sunmood on 2019/3/13.
 */
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException;
}
