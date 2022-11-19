package main.booking;

import main.flight.Flight;

import java.util.ArrayList;
public class BookingController {

    BookingService bookingService = new BookingService();

    public Booking makeBooking(Flight flight, String passName, String passSurname){
        return bookingService.makeBooking(flight, passName, passSurname);
    }

    public void cancelBooking(int id){
       bookingService.cancelBooking(id);
    }
    public ArrayList<Booking> getMyBookings(String name, String surname){
        return bookingService.getMyBookings(name, surname);
    }

    public void saveData(ArrayList<Booking> bookingList){
        bookingService.saveData(bookingList);
    }

    public ArrayList<Booking> getData() {
        return bookingService.getData();
    }

    public ArrayList<Booking> getBookingList(){
        return bookingService.getBookingList();
    }
}
