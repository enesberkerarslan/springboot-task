package com.JavaRestfulApi.springboottask.Services;

import com.JavaRestfulApi.springboottask.DTO.AirportDTO;
import com.JavaRestfulApi.springboottask.DTO.FlightDTO;
import com.JavaRestfulApi.springboottask.DTO.SearchFlightDTO;
import com.JavaRestfulApi.springboottask.Model.Airport;
import com.JavaRestfulApi.springboottask.Model.Flight;
import com.JavaRestfulApi.springboottask.Repository.AirportRepository;
import com.JavaRestfulApi.springboottask.Repository.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public FlightService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    // CREATE OR UPDATE
    public ResponseEntity<String> createOrUpdateFlight(FlightDTO flightDto) {
        try {
            if (flightDto.getFlightId() == null) {
                flightRepository.save(new Flight(
                        null,
                        flightDto.getDepartureAirportCode(),
                        flightDto.getArrivalAirportCode(),
                        flightDto.getDepartureDateTime(),
                        flightDto.getReturnDateTime(),
                        flightDto.getPrice()
                ));
                return new ResponseEntity<String>("Create successfully", HttpStatus.CREATED);
            } else {
                Optional<Flight> existingFlightOptional = flightRepository.findById(flightDto.getFlightId());

                if (existingFlightOptional.isPresent()) {
                    Flight existingFlight = existingFlightOptional.get();
                    existingFlight.setArrivalAirportCode(flightDto.getArrivalAirportCode());
                    existingFlight.setDepartureAirportCode(flightDto.getDepartureAirportCode());
                    existingFlight.setReturnDateTime(flightDto.getReturnDateTime());
                    existingFlight.setPrice(flightDto.getPrice());

                    flightRepository.save(existingFlight);
                    return new ResponseEntity<>("Update successfully", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }



    // READ
    public List<FlightDTO> getAllFlights() {
        return mapToFlightDto(flightRepository.findAll());

    }


    public Optional<Flight> getFlightById(Integer id) {
        return flightRepository.findById(id);
    }


    // DELETE
    public ResponseEntity<String> deleteFlight(Integer id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);

        if (flightOptional.isEmpty()) {
            return new ResponseEntity<>("Flight not found with id: " + id, HttpStatus.NOT_FOUND);
        }

        try {
            flightRepository.deleteById(id);
            return new ResponseEntity<>("Flight deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<SearchFlightDTO> searchFlights(String departure, String arrival, LocalDateTime departureDate) {

        List<SearchFlightDTO> flightDTOS = new ArrayList<>();
        if (departure == null || arrival == null || departureDate == null) {
            // Gerekli parametreler eksikse hata döndür
            throw new IllegalArgumentException("Departure, arrival, and departure date must be provided.");
        }

        List<Airport> departureAirports = airportRepository.findByCity(departure);
        List<Airport> arrivalAirports = airportRepository.findByCity(arrival);
        if (departureAirports.isEmpty() || arrivalAirports.isEmpty()) {
            // Belirtilen şehirler için havaalanı bulunamadı
            throw new IllegalArgumentException("No airports found for the specified departure and arrival cities.");
        }

        // Her iki şehirdeki havaalanlarına göre uçuşları bul
        for (Airport depAirport : departureAirports) {
            for (Airport arrAirport : arrivalAirports) {
                List<Flight> flights = flightRepository.findByDepartureAirportCodeAndArrivalAirportCodeAndDepartureDateTime(depAirport.getAirPortCode(), arrAirport.getAirPortCode(), departureDate);
                List<Flight> returnFlights = flightRepository.findByDepartureAirportCodeAndArrivalAirportCodeAndReturnDateTime(arrAirport.getAirPortCode(), depAirport.getAirPortCode(), departureDate);

                // Uçuşları DTO'ya dönüştür ve listeye ekle
                flightDTOS.addAll(mapToSearchFlightDto(flights));
                flightDTOS.addAll(mapToSearchFlightDto(returnFlights));
            }

        }
        return flightDTOS;
    }

    //DTO transfer
    private List<FlightDTO> mapToFlightDto(List<Flight> flightEntity) {

        List<FlightDTO> flightDTOS = new ArrayList<>();

        for(Flight flight : flightEntity)
        {
            flightDTOS.add(new FlightDTO(
                    flight.getFlightId(),
                    flight.getDepartureAirportCode(),
                    flight.getArrivalAirportCode(),
                    flight.getDepartureDateTime(),
                    flight.getReturnDateTime(),
                    flight.getPrice()
            ));
        }
        return flightDTOS;
    }

    private List<SearchFlightDTO> mapToSearchFlightDto(List<Flight> flightEntity) {

        List<SearchFlightDTO> searchFlightDTOS = new ArrayList<>();

        for(Flight flight : flightEntity)
        {
            searchFlightDTOS.add(new SearchFlightDTO(
                    flight.getDepartureAirportCode(),
                    flight.getArrivalAirportCode(),
                    flight.getDepartureDateTime(),
                    flight.getPrice()
            ));
        }
        return  searchFlightDTOS;
    }

}
