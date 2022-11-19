package booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import main.booking.Booking;
import main.booking.BookingService;
import main.flight.City;
import main.flight.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBookingService {
    BookingService bookingService = new BookingService();

    public TestBookingService() {
    }

    @Test
    public void testMakeBooking() {
        Flight flight = new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10);
        Booking expected = new Booking(flight, "Eliza", "Stone");
        Assertions.assertEquals(expected.getName(), bookingService.makeBooking(flight, "Eliza", "Stone").getName());
        Assertions.assertEquals(expected.getSurname(), bookingService.makeBooking(flight, "Eliza", "Stone").getSurname());
        Assertions.assertNotEquals(expected.getId(), bookingService.makeBooking(flight, "Eliza", "Stone").getId());
    }

    @Test
    public void testCancelBooking() {
        bookingService.makeBooking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone");
        bookingService.makeBooking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane");
        bookingService.makeBooking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Eliza", "Stone");
        Assertions.assertEquals(3, bookingService.getBookingList().size());
        bookingService.cancelBooking(((Booking)bookingService.getBookingList().get(1)).getId());
        Assertions.assertEquals(2, bookingService.getBookingList().size());
    }

    @Test
    public void testGetMyBookings() {
        bookingService.makeBooking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone");
        bookingService.makeBooking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Il", "Ge");
        bookingService.makeBooking(new Flight(19, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Elza", "Sen");
        bookingService.makeBooking(new Flight(65, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Il", "Ge");
        bookingService.makeBooking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Il", "Ge");
        ArrayList<Booking> expected = new ArrayList();
        expected.add(bookingService.getBookingList().get(1));
        expected.add(bookingService.getBookingList().get(3));
        expected.add(bookingService.getBookingList().get(4));
        Assertions.assertEquals(expected, bookingService.getMyBookings("Il", "Ge"));
    }

    @Test
    public void testGetBookingList() {
        bookingService.makeBooking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone");
        bookingService.makeBooking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane");
        bookingService.makeBooking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Eliza", "Stone");
        Assertions.assertEquals(3, bookingService.getBookingList().size());
        ArrayList<Booking> expected = new ArrayList();
        expected.add(bookingService.getBookingList().get(0));
        expected.add(bookingService.getBookingList().get(1));
        expected.add(bookingService.getBookingList().get(2));
        Assertions.assertEquals(expected, bookingService.getBookingList());
    }

    @Test
    public void testSaveAndGetData() {
        Assertions.assertEquals(0, bookingService.getBookingList().size());
        bookingService.makeBooking(new Flight(17, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone");
        bookingService.makeBooking(new Flight(14, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane");
        bookingService.makeBooking(new Flight(19, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.AMSTERDAM, 10), "Elza", "Sen");
        bookingService.makeBooking(new Flight(65, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.BOSTON, City.AMSTERDAM, 10), "Il", "Ge");
        bookingService.makeBooking(new Flight(27, LocalDate.of(2022, 10, 12), LocalTime.of(14, 30), City.KYIV, City.BERLIN, 10), "Ema", "Ste");
        bookingService.saveData(bookingService.getBookingList());
        Assertions.assertEquals(5, bookingService.getData().size());
    }
}
