package main.flight;

import java.util.ArrayList;

public interface FlightDAO<T> {
    ArrayList<T> getAllFlights();
    T getFlight(int id);
    T getFlight(T flight);
    ArrayList<T> loadFlights();
    void saveFlights(ArrayList<T> flights);
}