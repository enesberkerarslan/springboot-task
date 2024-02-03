package com.JavaRestfulApi.springboottask.Scheduled;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ManuelJobRunner {
    private final FlightScheduledJob scheduledJob;

    @Autowired
    public ManuelJobRunner(FlightScheduledJob scheduledJob) {
        this.scheduledJob = scheduledJob;
    }

    @PostConstruct
    public void runJobManually() {
        scheduledJob.fetchAndSaveFlights();
    }
}
