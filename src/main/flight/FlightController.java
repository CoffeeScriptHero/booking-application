package main.flight;

import java.util.ArrayList;
import java.util.Optional;

public class FlightController {
    FlightService flightService = new FlightService();

    public ArrayList<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    public void displayAllFlights() {
        flightService.displayAllFlights();
    }

    public void createFlights() {
        flightService.createFlights();
    }

    public Optional<Flight> getFlight(int id) {
        return flightService.getFlight(id);
    }

    public Optional<Flight> getFlight(Flight flight) {
        return flightService.getFlight(flight);
    }

    public void saveFlights(ArrayList<Flight> flights) {
        flightService.saveFlights(flights);
    }

    public ArrayList<Flight> loadFlights() {
        return flightService.loadFlights();
    }
}
