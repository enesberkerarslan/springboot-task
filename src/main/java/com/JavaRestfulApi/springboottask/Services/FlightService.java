package com.JavaRestfulApi.springboottask.Services;

import com.JavaRestfulApi.springboottask.Model.Flight;
import com.JavaRestfulApi.springboottask.Repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // CREATE
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // READ
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Integer id) {
        return flightRepository.findById(id);
    }

    // UPDATE
    public Flight updateFlight(Integer id, Flight updatedFlight) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);

        if (optionalFlight.isPresent()) {
            Flight existingFlight = optionalFlight.get();
            existingFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
            existingFlight.setArrivalAirport(updatedFlight.getArrivalAirport());
            existingFlight.setDepartureDateTime(updatedFlight.getDepartureDateTime());
            existingFlight.setReturnDateTime(updatedFlight.getReturnDateTime());
            existingFlight.setPrice(updatedFlight.getPrice());
            return flightRepository.save(existingFlight);
        } else {
            return null;
        }
    }

    // DELETE
    public void deleteFlight(Integer id) {
        flightRepository.deleteById(id);
    }
}
