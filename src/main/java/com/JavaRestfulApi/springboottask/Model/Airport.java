package com.JavaRestfulApi.springboottask.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
@Entity(name = "Airport")
@Table
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer airPortId;

    private String city;

    public Airport(Integer airPortId, String city) {
        this.airPortId = airPortId;
        this.city = city;
    }

    public Airport() {

    }

}
