package com.genius.webus.controller;

import com.genius.webus.entity.JwtRequest;
import com.genius.webus.entity.JwtResponse;
import com.genius.webus.entity.Ping;
import com.genius.webus.service.UserAuthService;
import com.genius.webus.utillity.JWTUtility;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/ping/{token}")
    public Ping ping(@PathVariable("token") String token) throws com.genius.webus.error.ExpiredJwtException {

        try {
            Ping ping = new Ping(jwtUtility.isTokenExpired(token));
            return ping;
        }catch (ExpiredJwtException e){

            throw new com.genius.webus.error.ExpiredJwtException("Token Expired");

        }

    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getPhoneNumber(), jwtRequest.getPassword()));
            System.out.println("here2");
        } catch (BadCredentialsException e){

            e.printStackTrace();
            throw new Exception("Invalid Credentials ", e);

        }

        final UserDetails userDetails = userAuthService.loadUserByUsername(jwtRequest.getPhoneNumber());
        final String token = jwtUtility.generateToken(userDetails);
        return new JwtResponse(token);

    }

}
