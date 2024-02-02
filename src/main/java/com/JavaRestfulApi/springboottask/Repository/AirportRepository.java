package com.JavaRestfulApi.springboottask.Repository;

import com.JavaRestfulApi.springboottask.Model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
}
