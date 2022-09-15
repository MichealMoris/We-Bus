package com.genius.webus.service.implemention;

import com.genius.webus.service.UserAuthService;
import com.genius.webus.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        com.genius.webus.entity.User user = userService.getUserByPhoneNumber(phoneNumber);

        return new User(user.getPhoneNumber(), user.getPassword(),new ArrayList<>());
    }
}
