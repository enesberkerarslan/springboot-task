package com.JavaRestfulApi.springboottask.Repository;

import com.JavaRestfulApi.springboottask.Model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

    @Modifying
    @Query("UPDATE Airport a SET a.city = :city WHERE a.airPortId = :airportId")
    int updateCityByAirportId(@Param("city") String city, @Param("airportId") Integer airportId);




}
