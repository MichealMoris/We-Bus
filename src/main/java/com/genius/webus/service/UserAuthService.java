package com.genius.webus.service;

import com.genius.webus.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public interface UserAuthService extends UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
