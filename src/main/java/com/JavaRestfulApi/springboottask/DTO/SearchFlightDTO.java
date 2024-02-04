package com.JavaRestfulApi.springboottask.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFlightDTO {
    private String departureAirportCode;
    private String arrivalAirportCode;
    private LocalDateTime departureDateTime;
    private Double price;
}
