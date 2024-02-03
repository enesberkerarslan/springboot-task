package com.JavaRestfulApi.springboottask.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {

    private Integer flightId;
    private String departureAirport;
    private String arrivalAirport;
    private Date departureDateTime;
    private Date returnDateTime;
    private Double price;


}
