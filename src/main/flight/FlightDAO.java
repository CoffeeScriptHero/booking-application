package main.flight;

import java.util.ArrayList;
import java.util.Optional;

public interface FlightDAO<T> {
    ArrayList<T> getAllFlights();
    Optional<T> getFlight(int id);
    Optional<T> getFlight(T flight);
    ArrayList<T> loadFlights();
    void saveFlights(ArrayList<T> flights);
}