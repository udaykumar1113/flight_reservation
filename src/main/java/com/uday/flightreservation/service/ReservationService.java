package com.uday.flightreservation.service;

import com.uday.flightreservation.dto.ReservationRequest;
import com.uday.flightreservation.entities.Reservation;

public interface ReservationService {
    Reservation bookFlight(ReservationRequest request);
}
