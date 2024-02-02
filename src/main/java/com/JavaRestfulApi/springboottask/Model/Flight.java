package com.JavaRestfulApi.springboottask.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Table
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer flightId;

    private String departureAirport;
    private String arrivalAirport;
    private Date departureDateTime;
    private Date returnDateTime;
    private Double price;

}
