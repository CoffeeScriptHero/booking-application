package flight;

import main.flight.City;
import main.flight.CollectionFlightDAO;
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

public class TestFlightDAO {
    private final CollectionFlightDAO flightDAO = CollectionFlightDAO.getInstance();
    private ArrayList<Flight> flights;
    private final Flight testFlight = new Flight(
            10050, LocalDate.of(2030, 12, 15), LocalTime.of(21, 0),
            City.KYIV, City.NEW_YORK, 25
    );

    @BeforeEach
    void setUp() {
        FlightService flightService = new FlightService();
        flightService.createFlights();
        this.flights = flightDAO.getAllFlights();
    }

    @AfterEach
    void tearDown() {
        this.flights = new ArrayList<>();
    }

    @Test
    void ExpectCollectionFlightDAOAlwaysTheSame() {
        CollectionFlightDAO newFlightDAO = CollectionFlightDAO.getInstance();
        assertEquals(flightDAO, newFlightDAO);
    }

    @Test
    void ExpectGetAllFlightsReturnsArrayListOfFlights() {
        ArrayList<Flight> possibleFlights = flightDAO.getAllFlights();
        assertEquals(possibleFlights.size(), 50);
        assertNotNull(possibleFlights.get(new Random().nextInt(0, 50)));
        assertNotNull(possibleFlights.get(new Random().nextInt(0, 50)));
    }

    @Test
    void ExpectGetFlightMethodsReturnNonEmptyOptional() {
        Flight flight = this.flights.get(0);
        assertNotEquals(flightDAO.getFlight(flight.getId()), Optional.empty());
        assertNotEquals(flightDAO.getFlight(flight), Optional.empty());
    }

    @Test
    void ExpectGetFlightMethodsReturnEmptyOptional() {
        assertEquals(flightDAO.getFlight(testFlight.getId()), Optional.empty());
        assertEquals(flightDAO.getFlight(testFlight), Optional.empty());
    }

    @Test
    void ExpectLoadFlightsMethodLoadsArrayListOfFlights() {
        flightDAO.saveFlights(new ArrayList<>());
        ArrayList<Flight> loadedFlights = flightDAO.loadFlights();
        assertEquals(loadedFlights.size(), 0);
        flightDAO.saveFlights(this.flights);
        loadedFlights = flightDAO.loadFlights();
        assertEquals(loadedFlights.size(), this.flights.size());
        assertEquals(loadedFlights, this.flights);
    }

    @Test
    void ExpectSaveFlightsMethodSavesArrayListOfFlights() {
        flightDAO.saveFlights(new ArrayList<>(List.of(this.testFlight)));
        ArrayList<Flight> loadedFlights = flightDAO.loadFlights();
        assertEquals(loadedFlights.size(), 1);
        assertEquals(loadedFlights.get(0), this.testFlight);
        flightDAO.saveFlights(new ArrayList<>());
        loadedFlights = flightDAO.loadFlights();
        assertEquals(loadedFlights.size(), 0);
        assertEquals(loadedFlights, new ArrayList<>());
        flightDAO.saveFlights(this.flights);
        loadedFlights = flightDAO.loadFlights();
        assertEquals(loadedFlights.size(), 50);
        assertEquals(loadedFlights, this.flights);
    }

    @Test
    void ExpectClearDatabaseMakesDatabaseEmpty() {
        assertTrue(flightDAO.loadFlights().size() > 0);
        flightDAO.clearDatabase();
        assertEquals(0, flightDAO.loadFlights().size());
    }

    @Test
    void ExpectSetFlightsWorksCorrectly() {
        assertEquals(flightDAO.getAllFlights().size(), 50);
        flightDAO.setFlights(new ArrayList<>());
        assertEquals(flightDAO.getAllFlights().size(), 0);
    }

    @Test
    void ExpectUpdateDatabaseUpdatesDatabase() {
        assertEquals(flightDAO.loadFlights().size(), 50);
        flightDAO.setFlights(new ArrayList<>());
        flightDAO.updateDatabase();
        assertEquals(flightDAO.loadFlights().size(), 0);
    }
}