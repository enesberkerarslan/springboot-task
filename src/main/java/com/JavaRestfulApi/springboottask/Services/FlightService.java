package com.JavaRestfulApi.springboottask.Services;

import com.JavaRestfulApi.springboottask.DTO.FlightDTO;
import com.JavaRestfulApi.springboottask.DTO.SearchFlightDTO;
import com.JavaRestfulApi.springboottask.Model.Airport;
import com.JavaRestfulApi.springboottask.Model.Flight;
import com.JavaRestfulApi.springboottask.Repository.FlightRepository;
import org.mapstruct.ap.shaded.freemarker.template.SimpleDate;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // CREATE
    public ResponseEntity<String> createOrUpdateAirport(FlightDTO flightDto) {
        try {
            if (flightDto.getFlightId() == null) {
                flightRepository.save(new Flight(
                        null,
                        flightDto.getDepartureAirport(),
                        flightDto.getArrivalAirport(),
                        flightDto.getDepartureDateTime(),
                        flightDto.getReturnDateTime(),
                        flightDto.getPrice()

                ));
                return new ResponseEntity<String>("Create succesfuly", HttpStatus.CREATED);

            } else {
                Optional<Flight> existingFlightOptional = flightRepository.findById(flightDto.getFlightId());

                if (existingFlightOptional.isPresent()) {
                    Flight existingFlight = existingFlightOptional.get();
                    existingFlight.setArrivalAirport(flightDto.getArrivalAirport());
                    existingFlight.setDepartureAirport(flightDto.getDepartureAirport());
                    existingFlight.setReturnDateTime(flightDto.getReturnDateTime());
                    existingFlight.setPrice(flightDto.getPrice());

                    flightRepository.save(existingFlight);
                    return new ResponseEntity<>("Update successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
                }
            }
        }
        catch(Exception e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);

        }
    }

    // READ
    public List<FlightDTO> getAllFlights() {
        return mapToDto(flightRepository.findAll());
        
    }


    public Optional<Flight> getFlightById(Integer id) {
        return flightRepository.findById(id);
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
                    flight.getFlightId(),
                    flight.getDepartureAirport(),
                    flight.getArrivalAirport(),
                    flight.getDepartureDateTime(),
                    flight.getReturnDateTime(),
                    flight.getPrice()
            ));
        }
        return flightDTOS;
    }


    public List<SearchFlightDTO> searchFlights(String departure, String arrival, LocalDateTime departureDate) {

        List<SearchFlightDTO> flightDTOS = new ArrayList<>();

        List<Flight> flights = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(departure, arrival, departureDate);
        List<Flight> returnFlights = flightRepository.findByDepartureAirportAndArrivalAirportAndReturnDateTime(arrival, departure, departureDate);

        for (Flight flight : flights) {
            flightDTOS.add(new SearchFlightDTO(
                    flight.getDepartureAirport(),
                    flight.getArrivalAirport(),
                    flight.getDepartureDateTime(),
                    flight.getPrice()
            ));
        }
        for (Flight flight : returnFlights) {
            flightDTOS.add(new SearchFlightDTO(
                    flight.getArrivalAirport(),
                    flight.getDepartureAirport(),
                    flight.getReturnDateTime(),
                    flight.getPrice()
            ));
        }
        return flightDTOS;
    }


}
