package com.uday.flightreservation.controller;

import com.uday.flightreservation.entities.Flight;
import com.uday.flightreservation.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @RequestMapping(value="findFlights", method= RequestMethod.GET)
    public String
            findFlights(@RequestParam("from") String from,
                        @RequestParam("to") String to,
                        @RequestParam("departureDate") @DateTimeFormat(pattern="MM-dd-yyyy")
                                      Date departureDate,
                                      ModelMap modelMap){
        List<Flight> flights= flightRepository.findFlights(from,to,departureDate);
        modelMap.addAttribute("flightList",flights);
        return "displayFlights";
    }
}
