package com.JavaRestfulApi.springboottask.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Entity(name = "Flight")
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;

    @Column(name = "departure_airport_code")
    private String departureAirportCode;

    @Column(name = "arrival_airport_code")
    private String arrivalAirportCode;

    private LocalDateTime departureDateTime;
    private LocalDateTime returnDateTime;

    private Double price;


}
