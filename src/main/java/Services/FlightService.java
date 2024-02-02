package Services;

import com.JavaRestfulApi.springboottask.Model.Flight;
import com.JavaRestfulApi.springboottask.Repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Optional<Flight> findFlightById(Integer flightId)
    {
        return flightRepository.findById(flightId);
    }
}
