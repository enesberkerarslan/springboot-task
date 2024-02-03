package com.JavaRestfulApi.springboottask.Controller;

import com.JavaRestfulApi.springboottask.DTO.AirportDTO;
import com.JavaRestfulApi.springboottask.DTO.FlightDTO;
import com.JavaRestfulApi.springboottask.Model.Airport;
import com.JavaRestfulApi.springboottask.Services.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping()
    public ResponseEntity<List<FlightDTO>> getAllFlights()
    {
        return new ResponseEntity<>(flightService.getAllFlights(),HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createOrUpdateAirport(@RequestBody FlightDTO flightDTO) {

        return flightService.createOrUpdateAirport(flightDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Integer id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>("Flight with ID " + id + " has been deleted.", HttpStatus.OK);
    }


}
