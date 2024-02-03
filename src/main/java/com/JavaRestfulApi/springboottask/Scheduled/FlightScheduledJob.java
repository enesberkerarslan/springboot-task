package com.JavaRestfulApi.springboottask.Scheduled;

import com.JavaRestfulApi.springboottask.DTO.FlightDTO;
import com.JavaRestfulApi.springboottask.Model.Flight;
import com.JavaRestfulApi.springboottask.Repository.FlightRepository;
import com.JavaRestfulApi.springboottask.Services.FlightService;
import org.mapstruct.ap.shaded.freemarker.template.SimpleDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class FlightScheduledJob {

    private final FlightService flightService;
    private final FlightRepository flightRepository;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Autowired
    public FlightScheduledJob(FlightService flightService, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
    }


    @Scheduled(cron = "0 0 0 * * *") // Her gün 00:00:00'da
    public void fetchAndSaveFlights() {
        List<Flight> mockFlights = getFlightsFromMockApi();

        if (mockFlights != null) {
            for (Flight flight : mockFlights) {
                flightRepository.save(flight);
            }
        }
        System.out.println("Scheduled job executed successfully.");
    }

    public Date parseDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Flight> getFlightsFromMockApi() {
        return Arrays.asList(
                new Flight(null, "Istanbul", "Ankara", parseDate("2024-02-05 12:00:00"), parseDate("2024-02-05 14:00:00"), 500.00),
                new Flight(null, "Istanbul", "Izmir", parseDate("2024-02-06 09:30:00"), parseDate("2024-02-06 11:30:00"), 600.00),
                new Flight(null, "Ankara", "Izmir", parseDate("2024-02-07 15:45:00"), parseDate("2024-02-07 17:45:00"), 550.00)
        );
    }


}
