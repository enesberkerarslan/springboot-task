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

    private String departureAirport;
    private String arrivalAirport;


    private LocalDateTime departureDateTime;
    private LocalDateTime returnDateTime;
    private Double price;


}
