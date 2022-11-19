package flight;

import main.flight.City;
import main.flight.Flight;
import main.flight.FlightService;
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

public class FlightServiceTest {
    private final FlightService flightService = new FlightService();
    private ArrayList<Flight> flights;
    private final Flight testFlight = new Flight(
            10050, LocalDate.of(2030, 12, 15), LocalTime.of(21, 0),
            City.KYIV, City.NEW_YORK, 25
    );

    @BeforeEach
    void setUp() {
        flightService.createFlights();
        this.flights = flightService.getAllFlights();
    }

    @AfterEach
    void tearDown() {
        this.flights = new ArrayList<>();
    }

    @Test
    void ExpectGetAllFlightsReturnsArrayListOfFlights() {
        ArrayList<Flight> possibleFlights = flightService.getAllFlights();
        assertEquals(possibleFlights.size(), 20);
        assertEquals(this.flights, possibleFlights);
        assertNotNull(possibleFlights.get(new Random().nextInt(0, 20)));
        assertNotNull(possibleFlights.get(new Random().nextInt(0, 20)));
    }

    @Test
    void ExpectFindAvailableFlightsReturnsArrayListWithRightFlights() {
        Flight firstFlight = this.flights.get(0);
        City destination = firstFlight.getDestination();
        LocalDate date = firstFlight.getDate();
        int peopleNumber = firstFlight.getAvailableSeats();

        ArrayList<Flight> availableFlights = flightService.findAvailableFlights(destination, date, peopleNumber);
        assertEquals(firstFlight, availableFlights.get(0));

        availableFlights = flightService.findAvailableFlights(testFlight.getDestination(), testFlight.getDate(), testFlight.getAvailableSeats());
        assertEquals(availableFlights.size(), 0);
    }

    @Test
    void ExpectGetFlightMethodsReturnNonEmptyOptional() {
        Flight flight = this.flights.get(0);
        assertNotEquals(flightService.getFlight(flight.getId()), Optional.empty());
        assertNotEquals(flightService.getFlight(flight), Optional.empty());
    }

    @Test
    void ExpectGetFlightMethodsReturnEmptyOptional() {
        assertEquals(flightService.getFlight(testFlight.getId()), Optional.empty());
        assertEquals(flightService.getFlight(testFlight), Optional.empty());
    }

    @Test
    void ExpectLoadFlightsMethodLoadsArrayListOfFlights() {
        flightService.saveFlights(new ArrayList<>());
        ArrayList<Flight> loadedFlights = flightService.loadFlights();
        assertEquals(loadedFlights.size(), 0);
        flightService.saveFlights(this.flights);
        loadedFlights = flightService.loadFlights();
        assertEquals(loadedFlights.size(), this.flights.size());
        assertEquals(loadedFlights, this.flights);
    }

    @Test
    void ExpectSaveFlightsMethodSavesArrayListOfFlights() {
        flightService.saveFlights(new ArrayList<>(List.of(this.testFlight)));
        ArrayList<Flight> loadedFlights = flightService.loadFlights();
        assertEquals(loadedFlights.size(), 1);
        assertEquals(loadedFlights.get(0), this.testFlight);
        flightService.saveFlights(new ArrayList<>());
        loadedFlights = flightService.loadFlights();
        assertEquals(loadedFlights.size(), 0);
        assertEquals(loadedFlights, new ArrayList<>());
        flightService.saveFlights(this.flights);
        loadedFlights = flightService.loadFlights();
        assertEquals(loadedFlights.size(), 20);
        assertEquals(loadedFlights, this.flights);
    }

    @Test
    void ExpectCreateFlightsCreatesFlightsAndSavesThemToDatabase() {
        ArrayList<Flight> loadedFlights = flightService.loadFlights();
        assertEquals(this.flights, loadedFlights);
        assertEquals(loadedFlights.size(), 20);
        flightService.saveFlights(new ArrayList<>());
        loadedFlights = flightService.loadFlights();
        assertNotEquals(this.flights, loadedFlights);
        assertEquals(loadedFlights.size(), 0);
        flightService.createFlights();
        loadedFlights = flightService.loadFlights();
        assertEquals(loadedFlights.size(), 20);
    }

    @Test
    void ExpectClearDatabaseMakesDatabaseEmpty() {
        assertTrue(flightService.loadFlights().size() > 0);
        flightService.clearDatabase();
        assertEquals(0, flightService.loadFlights().size());
    }

    @Test
    void ExpectSetFlightsWorksCorrectly() {
        assertEquals(flightService.getAllFlights().size(), 20);
        flightService.setFlights(new ArrayList<>());
        assertEquals(flightService.getAllFlights().size(), 0);
    }

    @Test
    void ExpectUpdateDatabaseUpdatesDatabase() {
        assertEquals(flightService.loadFlights().size(), 20);
        flightService.setFlights(new ArrayList<>());
        flightService.updateDatabase();
        assertEquals(flightService.loadFlights().size(), 0);
    }
}
