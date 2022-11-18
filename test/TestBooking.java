
import main.booking.BookingController;
import main.flight.City;
import main.flight.Flight;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestBooking {
    BookingController bookingController = new BookingController();

    @Test
    public void testAddBooking(){

        assertEquals(0, bookingController.getBookingList().size());
        bookingController.makeBooking(new Flight(17, (LocalDate.of(2022, 10, 12)),(LocalTime.of(14, 30)), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone");
        bookingController.makeBooking(new Flight(14, (LocalDate.of(2022, 10, 12)),(LocalTime.of(14, 30)), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane");
        bookingController.makeBooking(new Flight(27, (LocalDate.of(2022, 10, 12)),(LocalTime.of(14, 30)), City.KYIV, City.BERLIN, 10), "Eliza", "Stone");

        assertEquals(3, bookingController.getBookingList().size());
    }

    @Test
    public void testCancelBooking(){
        bookingController.makeBooking(new Flight(17, (LocalDate.of(2022, 10, 12)),(LocalTime.of(14, 30)), City.KYIV, City.AMSTERDAM, 10), "Eliza", "Stone");
        bookingController.makeBooking(new Flight(14, (LocalDate.of(2022, 10, 12)),(LocalTime.of(14, 30)), City.BOSTON, City.AMSTERDAM, 10), "Illy", "Vane");
        bookingController.makeBooking(new Flight(27, (LocalDate.of(2022, 10, 12)),(LocalTime.of(14, 30)), City.KYIV, City.BERLIN, 10), "Eliza", "Stone");
        assertEquals(3, bookingController.getBookingList().size());
        bookingController.cancelBooking(bookingController.getBookingList().get(1).getId());
        assertEquals(2, bookingController.getBookingList().size());
    }
}
