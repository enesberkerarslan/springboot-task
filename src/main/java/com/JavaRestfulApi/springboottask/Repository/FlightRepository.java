package com.JavaRestfulApi.springboottask.Repository;

import com.JavaRestfulApi.springboottask.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
