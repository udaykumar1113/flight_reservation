package com.uday.flightreservation.service;

import com.uday.flightreservation.entities.User;
import com.uday.flightreservation.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(userName);
        LOGGER.info("loaded username in UserDetailsServiceImpl is "+user.toString());
        if(user==null) {
            throw new UsernameNotFoundException("User not found for email" + userName);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),user.getRoles());
    }
}
