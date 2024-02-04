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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class FlightScheduledJob {

    private final FlightRepository flightRepository;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @Autowired
    public FlightScheduledJob(FlightRepository flightRepository) {
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

    public List<Flight> getFlightsFromMockApi() {
        return Arrays.asList(
                new Flight(null, "Istanbul Havaalanı", "Ankara Havaalanı", parseDate("2024-02-05 12:00"), parseDate("2024-02-05 14:00"), 500.00),
                new Flight(null, "Istanbul Havaalanı", "Izmir Havaalanı", parseDate("2024-02-06 09:30"), parseDate("2024-02-06 11:30"), 600.00),
                new Flight(null, "Ankara Havaalanı", "Izmir Havaalanı", parseDate("2024-02-07 15:45"), parseDate("2024-02-07 17:45"), 550.00)
        );
    }

    private LocalDateTime parseDate(String dateStr) {
        return LocalDateTime.parse(dateStr, formatter);
    }


}
