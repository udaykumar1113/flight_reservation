package com.uday.flightreservation.controller;

import com.uday.flightreservation.dto.ReservationUpdateRequest;
import com.uday.flightreservation.entities.Reservation;
import com.uday.flightreservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationRESTController {

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping(value="/getReservation/{id}", method=RequestMethod.GET)
    public Reservation getReservation(@PathVariable("id") Long id){
        return reservationRepository.getOne(id);
    }

    @RequestMapping(value="/updateReservation", method=RequestMethod.PUT)
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest reservationUpdateRequest){

        Reservation reservation=reservationRepository.getOne(reservationUpdateRequest.getId());

        reservation.setCheckedIn(reservationUpdateRequest.isCkeckedIn());
        reservation.setNumberOfBags(reservationUpdateRequest.getNumberOfBags());

        return reservationRepository.save(reservation);
    }
}
