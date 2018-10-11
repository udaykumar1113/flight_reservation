package com.uday.flightreservation.service;

import com.uday.flightreservation.entities.User;
import com.uday.flightreservation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(userName);
        if(user==null) {
            throw new UsernameNotFoundException("User not found for email" + userName);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),user.getRoles());
    }
}
