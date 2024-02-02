package com.JavaRestfulApi.springboottask.Services;

import com.JavaRestfulApi.springboottask.Model.Airport;
import com.JavaRestfulApi.springboottask.Repository.AirportRepository;
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
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    // READ
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Optional<Airport> getAirportById(Integer id) {
        return airportRepository.findById(id);
    }

    // UPDATE
    public Airport updateAirport(Integer id, Airport updatedAirport) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);

        if (optionalAirport.isPresent()) {
            Airport existingAirport = optionalAirport.get();
            existingAirport.setCity(updatedAirport.getCity());
            return airportRepository.save(existingAirport);
        } else {
            return null;
        }
    }

    // DELETE
    public void deleteAirport(Integer id) {
        airportRepository.deleteById(id);
    }


}
