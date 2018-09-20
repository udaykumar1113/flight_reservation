package com.uday.flightreservation.repositories;

import com.uday.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("from Flight where DEPARTURE_CITY=:from and ARRIVAL_CITY=:to and DATE_OF_DEPARTURE=:departureDate")
    List<Flight> findFlights(@Param("from") String from,
                             @Param("to") String to,
                             @Param("departureDate") Date departureDate);
}

