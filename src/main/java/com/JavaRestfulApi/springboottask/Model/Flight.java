package com.JavaRestfulApi.springboottask.Model;

import jakarta.persistence.*;
import lombok.*;

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
    private Date departureDateTime;
    private Date returnDateTime;
    private Double price;


}
