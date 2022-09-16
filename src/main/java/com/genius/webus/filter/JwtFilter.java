package com.genius.webus.filter;

import com.genius.webus.entity.User;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.service.UserAuthService;
import com.genius.webus.service.UserService;
import com.genius.webus.utillity.JWTUtility;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        String token = null;
        String phoneNumber = null;

        if (authorization != null && authorization.startsWith("Bearer ")){

            token = authorization.substring(7);
            phoneNumber = jwtUtility.getUsernameFromToken(token);

        }

        if (phoneNumber != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = userAuthService.loadUserByUsername(phoneNumber);


            if (jwtUtility.validateToken(token, userDetails)){
                User user = userService.getUserByPhoneNumber(phoneNumber);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, user, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }

        }

        filterChain.doFilter(request, response);

    }
}
