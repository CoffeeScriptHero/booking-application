package main.flight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class FlightController {
    FlightService flightService = new FlightService();

    public void clearDatabase() {
        flightService.clearDatabase();
    }

    public void updateDatabase() {
        flightService.updateDatabase();
    }

    public void setFlights(ArrayList<Flight> flights) {
        flightService.setFlights(flights);
    }

    public ArrayList<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    public ArrayList<Flight> findAvailableFlights(City destination, LocalDate date, int peopleNumber) {
        return flightService.findAvailableFlights(destination, date, peopleNumber);
    }

    public void displayAllFlights() {
        flightService.displayAllFlights();
    }

    public void displayUpcomingFlights() {
        flightService.displayUpcomingFlights();
    }

    public void displayFlights(ArrayList<Flight> flights) {
        flightService.displayFlights(flights);
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
