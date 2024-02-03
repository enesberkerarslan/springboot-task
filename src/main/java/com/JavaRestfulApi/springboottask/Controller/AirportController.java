package com.JavaRestfulApi.springboottask.Controller;

import com.JavaRestfulApi.springboottask.DTO.AirportDTO;
import com.JavaRestfulApi.springboottask.Model.Airport;
import com.JavaRestfulApi.springboottask.Services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/airport")
public class AirportController {

    private final AirportService airportService;


    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping()
    public ResponseEntity<List<Airport>> getAirports()
    {
        return new ResponseEntity<>(airportService.getAllAirports(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createOrUpdateAirport(@RequestBody AirportDTO airport) {

        return airportService.createOrUpdateAirport(airport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable Integer id) {
        airportService.deleteAirport(id);
        return new ResponseEntity<>("Airport with ID " + id + " has been deleted.", HttpStatus.OK);
    }

}
