package com.uday.flightreservation.controller;

import com.uday.flightreservation.entities.User;
import com.uday.flightreservation.repositories.UserRepository;
import com.uday.flightreservation.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecurityService securityService;

    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/showReg")
    public String showRegistration(){
        return "login/registerUser";
    }

    @RequestMapping(value="/registerUser", method= RequestMethod.POST)
    public String register(@ModelAttribute("user") User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login/login";
    }

    @RequestMapping(value="/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        ModelMap modelMap){
        LOGGER.info("Inside login with "+email+" "+password);
        //System.out.print("Inside login with "+email+" "+password);

        boolean loginResponse=securityService.login(email,password);

        if(loginResponse) {
            //System.out.print(userRepository.findByEmail(email)+" "+email);
            LOGGER.info("User able to login successful");
            LOGGER.error("error test");
            modelMap.addAttribute("msg","successful login");
            return "findFlights";
        }
            else{
            //System.out.print(userRepository.findByEmail(email)+" "+email);
                LOGGER.error("login failed");
                modelMap.addAttribute("msg","failure login");
        }
        return "login/login";
    }
}
