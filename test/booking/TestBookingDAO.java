package booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import main.booking.Booking;
import main.booking.CollectionBookingDAO;
import main.flight.City;
import main.flight.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBookingDAO {
    CollectionBookingDAO bookingDAO = new CollectionBookingDAO();

    public TestBookingDAO() {
    }

    @Test
    public void testGetBookingList() {
        bookingDAO.addBooking(new Booking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone"));
        bookingDAO.addBooking(new Booking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane"));
        bookingDAO.addBooking(new Booking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Eliza", "Stone"));
        Assertions.assertEquals(3, bookingDAO.getBookingList().size());
        ArrayList<Booking> expected = new ArrayList();
        expected.add(bookingDAO.getBookingList().get(0));
        expected.add(bookingDAO.getBookingList().get(1));
        expected.add(bookingDAO.getBookingList().get(2));
        Assertions.assertEquals(expected, bookingDAO.getBookingList());
    }

    @Test
    public void testAddBooking() {
        Assertions.assertEquals(0, bookingDAO.getBookingList().size());
        bookingDAO.addBooking(new Booking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone"));
        bookingDAO.addBooking(new Booking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane"));
        bookingDAO.addBooking(new Booking(new Flight(19, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Elza", "Sen"));
        bookingDAO.addBooking(new Booking(new Flight(65, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Il", "Ge"));
        bookingDAO.addBooking(new Booking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Ema", "Ste"));
        Assertions.assertEquals(5, bookingDAO.getBookingList().size());
    }

    @Test
    public void testCancelBooking() {
        bookingDAO.addBooking(new Booking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone"));
        bookingDAO.addBooking(new Booking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane"));
        bookingDAO.addBooking(new Booking(new Flight(19, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Elza", "Sen"));
        Assertions.assertEquals(3, bookingDAO.getBookingList().size());
        bookingDAO.cancelBooking((bookingDAO.getBookingList().get(1)).getId());
        Assertions.assertEquals(2, bookingDAO.getBookingList().size());
    }

    @Test
    public void testSaveAndGetData() {
        Assertions.assertEquals(0, bookingDAO.getBookingList().size());
        bookingDAO.addBooking(new Booking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone"));
        bookingDAO.addBooking(new Booking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane"));
        bookingDAO.addBooking(new Booking(new Flight(19, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Elza", "Sen"));
        bookingDAO.addBooking(new Booking(new Flight(65, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Il", "Ge"));
        bookingDAO.addBooking(new Booking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Ema", "Ste"));
        bookingDAO.saveData(bookingDAO.getBookingList());
        Assertions.assertEquals(5, bookingDAO.getData().size());
    }
}
