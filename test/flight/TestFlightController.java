
package flight;

import main.flight.City;
import main.flight.Flight;
import main.flight.FlightController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestFlightController {
    private final FlightController flightController = new FlightController();
    private ArrayList<Flight> flights;
    private final Flight testFlight = new Flight(
            10050, LocalDate.of(2030, 12, 15), LocalTime.of(21, 0),
            City.KYIV, City.NEW_YORK, 25
    );

    @BeforeEach
    void setUp() {
        flightController.createFlights();
        this.flights = flightController.getAllFlights();
    }

    @AfterEach
    void tearDown() {
        this.flights = new ArrayList<>();
    }

    @Test
    void ExpectGetAllFlightsReturnsArrayListOfFlights() {
        ArrayList<Flight> possibleFlights = flightController.getAllFlights();
        assertEquals(possibleFlights.size(), 50);
        assertEquals(this.flights, possibleFlights);
        assertNotNull(possibleFlights.get(new Random().nextInt(0, 50)));
        assertNotNull(possibleFlights.get(new Random().nextInt(0, 50)));
    }

    @Test
    void ExpectFindAvailableFlightsReturnsArrayListWithRightFlights() {
        Flight firstFlight = this.flights.get(0);
        City destination = firstFlight.getDestination();
        LocalDate date = firstFlight.getDate();
        int peopleNumber = firstFlight.getAvailableSeats();

        ArrayList<Flight> availableFlights = flightController.findAvailableFlights(destination, date, peopleNumber);
        assertEquals(firstFlight, availableFlights.get(0));

        availableFlights = flightController.findAvailableFlights(testFlight.getDestination(), testFlight.getDate(), testFlight.getAvailableSeats());
        assertEquals(availableFlights.size(), 0);
    }

    @Test
    void ExpectGetFlightMethodsReturnNonEmptyOptional() {
        Flight flight = this.flights.get(0);
        assertNotEquals(flightController.getFlight(flight.getId()), Optional.empty());
        assertNotEquals(flightController.getFlight(flight), Optional.empty());
    }

    @Test
    void ExpectGetFlightMethodsReturnEmptyOptional() {
        assertEquals(flightController.getFlight(testFlight.getId()), Optional.empty());
        assertEquals(flightController.getFlight(testFlight), Optional.empty());
    }

    @Test
    void ExpectLoadFlightsMethodLoadsArrayListOfFlights() {
        flightController.saveFlights(new ArrayList<>());
        ArrayList<Flight> loadedFlights = flightController.loadFlights();
        assertEquals(loadedFlights.size(), 0);
        flightController.saveFlights(this.flights);
        loadedFlights = flightController.loadFlights();
        assertEquals(loadedFlights.size(), this.flights.size());
        assertEquals(loadedFlights, this.flights);
    }

    @Test
    void ExpectSaveFlightsMethodSavesArrayListOfFlights() {
        flightController.saveFlights(new ArrayList<>(List.of(this.testFlight)));
        ArrayList<Flight> loadedFlights = flightController.loadFlights();
        assertEquals(loadedFlights.size(), 1);
        assertEquals(loadedFlights.get(0), this.testFlight);
        flightController.saveFlights(new ArrayList<>());
        loadedFlights = flightController.loadFlights();
        assertEquals(loadedFlights.size(), 0);
        assertEquals(loadedFlights, new ArrayList<>());
        flightController.saveFlights(this.flights);
        loadedFlights = flightController.loadFlights();
        assertEquals(loadedFlights.size(), 50);
        assertEquals(loadedFlights, this.flights);
    }

    @Test
    void ExpectCreateFlightsCreatesFlightsAndSavesThemToDatabase() {
        ArrayList<Flight> loadedFlights = flightController.loadFlights();
        assertEquals(this.flights, loadedFlights);
        assertEquals(loadedFlights.size(), 50);
        flightController.saveFlights(new ArrayList<>());
        loadedFlights = flightController.loadFlights();
        assertNotEquals(this.flights, loadedFlights);
        assertEquals(loadedFlights.size(), 0);
        flightController.createFlights();
        loadedFlights = flightController.loadFlights();
        assertEquals(loadedFlights.size(), 50);
    }

    @Test
    void ExpectClearDatabaseMakesDatabaseEmpty() {
        assertTrue(flightController.loadFlights().size() > 0);
        flightController.clearDatabase();
        assertEquals(0, flightController.loadFlights().size());
    }

    @Test
    void ExpectSetFlightsWorksCorrectly() {
        assertEquals(flightController.getAllFlights().size(), 50);
        flightController.setFlights(new ArrayList<>());
        assertEquals(flightController.getAllFlights().size(), 0);
    }

    @Test
    void ExpectUpdateDatabaseUpdatesDatabase() {
        assertEquals(flightController.loadFlights().size(), 50);
        flightController.setFlights(new ArrayList<>());
        flightController.updateDatabase();
        assertEquals(flightController.loadFlights().size(), 0);
    }
}