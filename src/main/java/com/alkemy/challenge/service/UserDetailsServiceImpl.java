package com.alkemy.challenge.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(s.equals("admin")){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            var userDetails = new User("admin", passwordEncoder.encode("admin"), grantedAuthorities);
            return userDetails;
        }
        if(s.equals("user")){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            var userDetails = new User("admin", passwordEncoder.encode("user"), grantedAuthorities);
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found");
    }
}

