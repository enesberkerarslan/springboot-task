package com.JavaRestfulApi.springboottask.Services;

import com.JavaRestfulApi.springboottask.DTO.FlightDTO;
import com.JavaRestfulApi.springboottask.Model.Flight;
import com.JavaRestfulApi.springboottask.Repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<FlightDTO> getAllFlights() {
        return mapToDto(flightRepository.findAll());
        
    }


    public Optional<Flight> getFlightById(Integer id) {
        return flightRepository.findById(id);
    }

    // UPDATE
    public Flight updateFlight(Integer id, Flight updatedFlight) {
        return null;
    }

    // DELETE
    public void deleteFlight(Integer id) {
        flightRepository.deleteById(id);
    }

    //DTO transfer
    private List<FlightDTO> mapToDto(List<Flight> flightEntity) {

        List<FlightDTO> flightDTOS = new ArrayList<>();

        for(Flight flight : flightEntity)
        {
            flightDTOS.add(new FlightDTO(
                    flight.getDepartureAirport(),
                    flight.getArrivalAirport(),
                    flight.getDepartureDateTime(),
                    flight.getReturnDateTime(),
                    flight.getPrice()
            ));
        }
        return flightDTOS;
    }
}
