package com.uday.flightreservation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger LOGGER= LoggerFactory.getLogger(SecurityServiceImpl.class);
    @Override
    public boolean login(String userName, String password) {

        LOGGER.info("UserDetailsService class "+userDetailsService.getClass()+" tosting value "+userDetailsService.toString());
        UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
        LOGGER.info(userDetails.toString());
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
