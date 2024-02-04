package com.JavaRestfulApi.springboottask.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {

    private Integer flightId;
    private String departureAirportCode;
    private String arrivalAirportCode;
    private LocalDateTime departureDateTime;
    private LocalDateTime returnDateTime;
    private Double price;


}
