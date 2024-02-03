package com.JavaRestfulApi.springboottask.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity(name = "Flight")
@Table
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
