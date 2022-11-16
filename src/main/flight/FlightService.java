package main.flight;

import main.logger.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightService {
    final CollectionFlightDAO flightDao = CollectionFlightDAO.getInstance();

    public ArrayList<Flight> getAllFlights() {
        Logger.info("Flights have been received");
        return flightDao.getAllFlights();
    }

    public void displayAllFlights() {
        getAllFlights().forEach(Flight::prettyFormat);
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
        ArrayList<Flight> flights = new ArrayList<>(List.of(
                new Flight(
                        LocalDate.of(2022, 12, 8), LocalTime.of(19, 15),
                        City.KYIV, City.BERLIN, 31
                ),
                new Flight(
                        LocalDate.of(2022, 12, 8), LocalTime.of(2, 30),
                        City.ISTANBUL, City.BERLIN, 11
                ),
                new Flight(
                        LocalDate.of(2022, 11, 29), LocalTime.of(3, 0),
                        City.LONDON, City.NEW_YORK, 17
                ),
                new Flight(
                        LocalDate.of(2022, 12, 1), LocalTime.of(12, 35),
                        City.MIAMI, City.LAS_VEGAS, 2
                ),
                new Flight(
                        LocalDate.of(2022, 12, 26), LocalTime.of(5, 45),
                        City.WARSAW, City.PRAGUE, 23
                ),
                new Flight(
                        LocalDate.of(2022, 12, 6), LocalTime.of(21, 0),
                        City.TALLINN, City.ODESSA, 8
                ),
                new Flight(
                        LocalDate.of(2022, 11, 30), LocalTime.of(14, 40),
                        City.TEL_AVIV, City.TORONTO, 8
                ),
                new Flight(
                        LocalDate.of(2022, 12, 1), LocalTime.of(5, 15),
                        City.BOSTON, City.TORONTO, 4
                ),
                new Flight(
                        LocalDate.of(2022, 12, 6), LocalTime.of(8, 40),
                        City.CAIRO, City.LVIV, 16
                ),
                new Flight(
                        LocalDate.of(2022, 12, 6), LocalTime.of(12, 0),
                        City.PARIS, City.TOKYO, 1
                ),
                new Flight(
                        LocalDate.of(2022, 12, 21), LocalTime.of(20, 30),
                        City.KYIV, City.NEW_YORK, 5
                ),
                new Flight(
                        LocalDate.of(2022, 12, 24), LocalTime.of(13, 5),
                        City.LAS_VEGAS, City.PRAGUE, 24
                ),
                new Flight(
                        LocalDate.of(2022, 11, 19), LocalTime.of(14, 30),
                        City.LVIV, City.AMSTERDAM, 10
                ),
                new Flight(
                        LocalDate.of(2022, 11, 14), LocalTime.of(5, 0),
                        City.ODESSA, City.PARIS, 17
                ),
                new Flight(
                        LocalDate.of(2022, 12, 17), LocalTime.of(21, 15),
                        City.KYIV, City.MALDIVES, 25
                ),
                new Flight(
                        LocalDate.of(2022, 12, 30), LocalTime.of(13, 30),
                        City.MADRID, City.BALI, 3
                ),
                new Flight(
                        LocalDate.of(2023, 1, 3), LocalTime.of(10, 0),
                        City.TOKYO, City.SEOUL, 3
                ),
                new Flight(
                        LocalDate.of(2023, 1, 15), LocalTime.of(23, 40),
                        City.DUBAI, City.BALI, 9
                ),
                new Flight(
                        LocalDate.of(2023, 1, 23), LocalTime.of(11, 0),
                        City.PRAGUE, City.DUBAI, 9
                ),
                new Flight(
                        LocalDate.of(2023, 1, 23), LocalTime.of(19, 30),
                        City.BERLIN, City.DUBAI, 14
                )
        ));

        saveFlights(flights);
    }
}
