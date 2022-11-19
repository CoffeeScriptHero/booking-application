package main.flight;

import main.logger.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class FlightService {
    final CollectionFlightDAO flightDao = CollectionFlightDAO.getInstance();

    public void clearDatabase() {
        flightDao.clearDatabase();
    }

    public void updateDatabase() {
        flightDao.updateDatabase();
    }

    public void setFlights(ArrayList<Flight> flights) {
        flightDao.setFlights(flights);
    }

    public ArrayList<Flight> getAllFlights() {
        Logger.info("Flights have been received");
        return flightDao.getAllFlights();
    }

    public ArrayList<Flight> findAvailableFlights(City destination, LocalDate date, int peopleNumber) {
        return flightDao.findAvailableFlights(destination, date, peopleNumber);
    }

    public void displayAllFlights() {
        getAllFlights().forEach(Flight::prettyFormat);
    }

    public void displayUpcomingFlights() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        getAllFlights().forEach(flight -> {
            if (ChronoUnit.HOURS.between(currentDateTime, flight.getLocalDateTime()) <= 24) {
                flight.prettyFormat();
            }
        });
    }

    public void displayFlights(ArrayList<Flight> flights) {
        flights.forEach(Flight::prettyFormat);
    }

    public Optional<Flight> getFlight(int id) {
        return flightDao.getFlight(id);
    }

    public Optional<Flight> getFlight(Flight flight) {
        return flightDao.getFlight(flight);
    }

    public void saveFlights(ArrayList<Flight> flights) {
        flightDao.saveFlights(flights);
    }

    public ArrayList<Flight> loadFlights() {
        return flightDao.loadFlights();
    }

    public void createFlights() {
        ArrayList<Flight> flights = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            City origin = City.randomCity();
            flights.add(new Flight(generateDate(), generateTime(), origin, City.randomCity(origin), generateSeats()));
        }
        flightDao.setFlights(flights);
        flightDao.updateDatabase();
    }

    private LocalDate generateDate() {
        int days = new Random().nextInt(3);
        return LocalDate.now().plusDays(days);
    }

    private LocalTime generateTime() {
        Random random = new Random();
        int[] minutesArray = {0, 10, 15, 30, 45};
        int hours = new Random().nextInt(0, 24);
        int minutes = minutesArray[random.nextInt(minutesArray.length)];
        return LocalTime.of(hours, minutes);
    }

    private int generateSeats() {
        return new Random().nextInt(1, 35);
    }
}