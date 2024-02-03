package com.JavaRestfulApi.springboottask.Services;

import com.JavaRestfulApi.springboottask.DTO.AirportDTO;
import com.JavaRestfulApi.springboottask.Model.Airport;
import com.JavaRestfulApi.springboottask.Repository.AirportRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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



    // CREATE
    public ResponseEntity<String> createOrUpdateAirport(AirportDTO airportDto) {
        try {
            if (airportDto.getAirportId() == null) {
                airportRepository.save(new Airport(
                        null,
                        airportDto.getAirportName()
                ));
                return new ResponseEntity<String>("Create succesfuly", HttpStatus.CREATED);

            } else {
                Airport airPort = airportRepository.findById(airportDto.getAirportId()).orElseThrow();
                airPort.setCity(airportDto.getAirportName());
                airportRepository.save(airPort);
                return new ResponseEntity<String>("Update succesfuly", HttpStatus.CREATED);
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

    // UPDATE
    public void updateAirport(Integer airportId, String newCity) {
        int updatedRows = airportRepository.updateCityByAirportId(newCity, airportId);

        if (updatedRows > 0) {
            System.out.println("Airport updated successfully");
        } else {
            System.out.println("Airport update failed");
        }
    }



    // DELETE
    public void deleteAirport(Integer id) {
        airportRepository.deleteById(id);
    }


}
