package com.JavaRestfulApi.springboottask.Controller;

import com.JavaRestfulApi.springboottask.DTO.SearchFlightDTO;
import com.JavaRestfulApi.springboottask.Model.Flight;
import com.JavaRestfulApi.springboottask.Services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private FlightService flightService;


    @Autowired
    public SearchController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public ResponseEntity<Object> searchFlights(
            @RequestParam(name = "departure") String departure,
            @RequestParam(name = "arrival") String arrival,
            @RequestParam(name = "departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate,
            @RequestParam(name = "returnDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDate) {

        try {
            if (returnDate != null) {
                List<SearchFlightDTO> oneWayFlights = flightService.searchFlights(departure, arrival, departureDate);
                List<SearchFlightDTO> returnFlights = flightService.searchFlights(arrival, departure, returnDate);

                return ResponseEntity.ok(Map.of("Departure Flights", oneWayFlights, "Return Flights", returnFlights));
            } else {
                List<SearchFlightDTO> oneWayFlights = flightService.searchFlights(departure, arrival, departureDate);
                return ResponseEntity.ok(Map.of("One Way Flights", oneWayFlights));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }


}
