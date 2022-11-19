package booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import main.booking.Booking;
import main.booking.BookingController;
import main.flight.City;
import main.flight.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBookingController {
    BookingController bookingController = new BookingController();

    public TestBookingController() {
    }

    @Test
    public void testAddBooking() {
        Assertions.assertEquals(0, bookingController.getBookingList().size());
        bookingController.makeBooking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone");
        bookingController.makeBooking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane");
        bookingController.makeBooking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Eliza", "Stone");
        Assertions.assertEquals(3, bookingController.getBookingList().size());
    }

    @Test
    public void testCancelBooking() {
        bookingController.makeBooking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone");
        bookingController.makeBooking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane");
        bookingController.makeBooking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Eliza", "Stone");
        Assertions.assertEquals(3, bookingController.getBookingList().size());
        bookingController.cancelBooking(((Booking)bookingController.getBookingList().get(1)).getId());
        Assertions.assertEquals(2, bookingController.getBookingList().size());
    }

    @Test
    public void testGetBookingList() {
        bookingController.makeBooking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone");
        bookingController.makeBooking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane");
        bookingController.makeBooking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Eliza", "Stone");
        Assertions.assertEquals(3, bookingController.getBookingList().size());
        Assertions.assertEquals("[\nBooking id: " + ((Booking)bookingController.getBookingList().get(0)).getId() + "\nRace: 17\nFlight from KYIV to AMSTERDAM\nDate 2022-10-12, Time 14:30\nName Eliza Stone, " +
                "\nBooking id: " + ((Booking)bookingController.getBookingList().get(1)).getId() + "\nRace: 14\nFlight from BOSTON to AMSTERDAM\nDate 2022-10-12, Time 14:30\nName Illy Vane, " +
                "\nBooking id: " + ((Booking)bookingController.getBookingList().get(2)).getId() + "\nRace: 27\nFlight from KYIV to BERLIN\nDate 2022-10-12, Time 14:30\nName Eliza Stone]",
                bookingController.getBookingList().toString());
    }

    @Test
    public void testSaveAndGetData() {
        Assertions.assertEquals(0, bookingController.getBookingList().size());
        bookingController.makeBooking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone");
        bookingController.makeBooking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane");
        bookingController.makeBooking(new Flight(19, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Elza", "Sen");
        bookingController.makeBooking(new Flight(65, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Il", "Ge");
        bookingController.makeBooking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Ema", "Ste");
        bookingController.saveData(bookingController.getBookingList());
        Assertions.assertEquals(5, bookingController.getData().size());
    }

    @Test
    public void testGetMyBookings() {
        bookingController.makeBooking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone");
        bookingController.makeBooking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Il", "Ge");
        bookingController.makeBooking(new Flight(19, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Elza", "Sen");
        bookingController.makeBooking(new Flight(65, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Il", "Ge");
        bookingController.makeBooking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Il", "Ge");
        ArrayList<Booking> expected = new ArrayList();
        expected.add(bookingController.getBookingList().get(1));
        expected.add(bookingController.getBookingList().get(3));
        expected.add(bookingController.getBookingList().get(4));
        Assertions.assertEquals(expected, bookingController.getMyBookings("Il", "Ge"));
    }
}
