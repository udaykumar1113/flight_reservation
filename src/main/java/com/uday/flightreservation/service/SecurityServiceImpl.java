package com.uday.flightreservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean login(String userName, String password) {

        UserDetails userDetails=userDetailsService.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken token=new
                UsernamePasswordAuthenticationToken(userDetails,password, userDetails.getAuthorities());

        authenticationManager.authenticate(token);

        boolean result=token.isAuthenticated();
        if(result){
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        return result;
    }
}
