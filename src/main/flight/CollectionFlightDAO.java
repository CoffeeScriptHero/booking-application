package main.flight;

import main.logger.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectionFlightDAO implements FlightDAO<Flight> {
    private static final CollectionFlightDAO collectionFlightDAO = new CollectionFlightDAO();
    private final String DATABASE = "src/main/database/flights.txt";
    private ArrayList<Flight> flights = loadFlights();

    private CollectionFlightDAO() {
    }

    public static CollectionFlightDAO getInstance() {
        return collectionFlightDAO;
    }

    public void clearDatabase() {
        saveFlights(new ArrayList<>());
    }

    public ArrayList<Flight> findAvailableFlights(City destination, LocalDate date, int peopleNumber) {
        return (ArrayList<Flight>) flights.stream().filter(flight ->
                flight.getDestination().getCityName().equals(destination.getCityName())
                        && flight.getDate().equals(date)
                        && flight.getAvailableSeats() >= peopleNumber).collect(Collectors.toList());
    }

    @Override
    public ArrayList<Flight> getAllFlights() {
        return flights;
    }

    @Override
    public Optional<Flight> getFlight(int id) {
        return flights.stream().filter(flight -> flight.getId() == id).findAny();
    }

    @Override
    public Optional<Flight> getFlight(Flight flight) {
        return flights.stream().filter(flight::equals).findAny();
    }

    @Override
    public ArrayList<Flight> loadFlights() {
        ArrayList<Flight> flightsFromFile = new ArrayList<>();
        try (
                FileInputStream fis = new FileInputStream(DATABASE);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            while (fis.available() > 0) {
                flightsFromFile.add((Flight) ois.readObject());
            }
            Logger.info("Flights were loaded from file");
            this.flights = flightsFromFile;
        } catch (IOException | ClassNotFoundException exception) {
            Logger.info("Flights were not loaded from file");
        }
        return flightsFromFile;
    }

    @Override
    public void saveFlights(ArrayList<Flight> flights) {
        try (
                FileOutputStream fos = new FileOutputStream(DATABASE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            for (Flight flight : flights) {
                oos.writeObject(flight);
            }
            Logger.info("Flights are saved to a file");
        } catch (IOException exception) {
            Logger.error("Flights are not saved to a file");
        }
    }
}