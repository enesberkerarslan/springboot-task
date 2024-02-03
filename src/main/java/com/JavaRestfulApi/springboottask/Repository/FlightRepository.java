package com.JavaRestfulApi.springboottask.Repository;

import com.JavaRestfulApi.springboottask.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTime(
            String departure, String arrival, LocalDateTime departureDate);

    List<Flight> findByDepartureAirportAndArrivalAirportAndReturnDateTime(
            String departure, String arrival, LocalDateTime returnDate);
}
