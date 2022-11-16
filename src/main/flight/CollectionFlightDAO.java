package main.flight;

import java.util.ArrayList;

public class CollectionFlightDAO implements FlightDAO<Flight> {
    private static CollectionFlightDAO collectionFlightDAO;
    private final ArrayList<Flight> flights = new ArrayList<>();
    private final String DATABASE = "flights.txt";

    public static CollectionFlightDAO getInstance() {
        if (collectionFlightDAO == null) {
            collectionFlightDAO = new CollectionFlightDAO();
        }
        return collectionFlightDAO;
    }

    @Override
    public ArrayList<Flight> getAllFlights() {
        return flights;
    }

    @Override
    public Flight getFlight(int id) {
        return flights.stream().filter(flight -> flight.getId() == id).findAny().orElse(null);
    }

    @Override
    public Flight getFlight(Flight flight) {
        return null;
    }

    @Override
    public ArrayList<Flight> loadFlights() {
        return null;
    }

    @Override
    public void saveFlights(ArrayList<Flight> flights) {

    }
}