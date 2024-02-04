package com.JavaRestfulApi.springboottask.Services;

import com.JavaRestfulApi.springboottask.DTO.AirportDTO;
import com.JavaRestfulApi.springboottask.Model.Airport;
import com.JavaRestfulApi.springboottask.Repository.AirportRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;


    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Optional<Airport> findAirportById(Integer airportId)
    {
        return airportRepository.findById(airportId);
    }



    // CREATE OR UPDATE
    public ResponseEntity<String> createOrUpdateAirport(AirportDTO airportDto) {
        try {
            if (airportDto.getCity() == null || airportDto.getAirPortCode() == null) {
                return new ResponseEntity<>("Invalid input data. City and Airport Code must be provided.", HttpStatus.BAD_REQUEST);
            }
            if (airportDto.getAirportId() == null) {
                airportRepository.save(new Airport(
                        null,
                        airportDto.getCity(),
                        airportDto.getAirPortCode()

                ));
                return new ResponseEntity<>("Airport created successfully", HttpStatus.CREATED);

            } else {
                Airport existingAirport = airportRepository.findById(airportDto.getAirportId()).orElseThrow(() -> new RuntimeException("Airport not found with id: " + airportDto.getAirportId()));
                existingAirport.setCity(airportDto.getCity());
                airportRepository.save(existingAirport);
                return new ResponseEntity<>("Airport updated successfully", HttpStatus.OK);
            }
        }
        catch(Exception e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);

        }
    }

    // READ
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Optional<Airport> getAirportById(Integer id) {
        return airportRepository.findById(id);
    }



    // DELETE
    public ResponseEntity<String> deleteAirport(Integer id) {
        Optional<Airport> airportOptional = airportRepository.findById(id);
        if (airportOptional.isEmpty()) {
            return new ResponseEntity<>("Airport not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        try {
            airportRepository.deleteById(id);
            return new ResponseEntity<>("Airport deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
