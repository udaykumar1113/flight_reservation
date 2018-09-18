package com.uday.flightreservation.controller;

import com.uday.flightreservation.entities.User;
import com.uday.flightreservation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/showReg")
    public String showRegistration(){
        return "login/registerUser";
    }

    @RequestMapping(value="/registerUser", method= RequestMethod.POST)
    public String register(@ModelAttribute("user") User user){
        userRepository.save(user);
        return "login/login";
    }

    @RequestMapping(value="/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        ModelMap modelMap){

        System.out.print("Inside login with "+email+" "+password);
        if(userRepository.findByEmail(email).getEmail().equals(email)) {
            System.out.print(userRepository.findByEmail(email)+" "+email);
            modelMap.addAttribute("msg","successful login");
            return "login/login";
        }
            else{
            System.out.print(userRepository.findByEmail(email)+" "+email);
                modelMap.addAttribute("msg","failure login");
        }
        return "login/login";
    }
}
