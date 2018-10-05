package com.uday.flightreservation.controller;

import com.uday.flightreservation.dto.ReservationRequest;
import com.uday.flightreservation.entities.Flight;
import com.uday.flightreservation.entities.Reservation;
import com.uday.flightreservation.repositories.FlightRepository;
import com.uday.flightreservation.service.ReservationService;
import com.uday.flightreservation.utility.EmailUtility;
import com.uday.flightreservation.utility.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservationController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EmailUtility emailUtility;

    @Autowired
    PdfGenerator pdfGenerator;

    @RequestMapping(value="showCompleteReservation/{id}", method = RequestMethod.GET)
    public String showCompleteReservation(@PathVariable("id") Long id, ModelMap modelMap){
        System.out.println("Reservation is for id: "+id);
        Flight flight= flightRepository.getOne(id);
        System.out.println("Selected flight details are: "+flight);
        modelMap.addAttribute("flight",flight);
        return "completeReservation";
    }

    @RequestMapping(value="/completeReservation", method=RequestMethod.POST)
    public String completeReservation(@ModelAttribute("reservationRequest") ReservationRequest reservationRequest,
                                      ModelMap modelMap){
        System.out.println(reservationRequest);
        Reservation savedReservation=reservationService.bookFlight(reservationRequest);

        pdfGenerator.generatePdfFile(savedReservation,
                System.getProperty("user.home")+"/Downloads/flight_itenary_"+savedReservation.getId()+".pdf");

        emailUtility.sendMail("udaykumar1113@gmail.com","Saved reservation notification","Reservation saved successfully"+savedReservation.toString());

        modelMap.addAttribute("msg","Reservation saved successfully");
        return "reservationConfirmation";
    }
}
