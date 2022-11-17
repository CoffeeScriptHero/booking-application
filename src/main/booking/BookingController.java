package main.booking;

import main.flight.Flight;

import java.util.ArrayList;
import java.util.List;

public class BookingController {

    BookingService bookingService = new BookingService();

    public Booking makeBooking(Flight flight, String passName, String passSurname){
        return bookingService.makeBooking(flight, passName, passSurname);
    }

    public void cancelBooking(int id){
       bookingService.cancelBooking(id);
    }
    public List<Booking> getMyBookings(String name, String surname){
        return bookingService.getMyBookings(name, surname);
    }

    public void saveData(ArrayList<Booking> bookingList){
        bookingService.saveData(bookingList);
    }

    public ArrayList<Booking> getData() {
        return bookingService.getData();
    }

    public List<Booking> getBookingList(){
        return bookingService.getBookingList();
    }
}
