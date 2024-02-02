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

    public Flight(Integer flightId, String departureAirport, String arrivalAirport, Date departureDateTime, Date returnDateTime, Double price) {
        this.flightId = flightId;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDateTime = departureDateTime;
        this.returnDateTime = returnDateTime;
        this.price = price;
    }

    public Flight() {

    }
}
