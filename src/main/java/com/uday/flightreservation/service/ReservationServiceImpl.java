package com.uday.flightreservation.service;

import com.uday.flightreservation.dto.ReservationRequest;
import com.uday.flightreservation.entities.Flight;
import com.uday.flightreservation.entities.Passenger;
import com.uday.flightreservation.entities.Reservation;
import com.uday.flightreservation.repositories.FlightRepository;
import com.uday.flightreservation.repositories.PassengerRepository;
import com.uday.flightreservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Reservation bookFlight(ReservationRequest request) {
        Flight flight=flightRepository.getOne(request.getFlightId());

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setMiddlename("test");
        passenger.setPhone(request.getPassengerPhone());
        passenger.setEmail(request.getPassengerEmail());
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        Reservation savedReservation= reservationRepository.save(reservation);

        return savedReservation;
    }
}
