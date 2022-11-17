package main.booking;

import java.util.ArrayList;
import java.util.List;

public interface BookingDAO {


    List<Booking> getBookingList();

    void addBooking(Booking booking);
    void cancelBooking(int id);

    void saveData(ArrayList<Booking> bookingList);

   ArrayList<Booking> getData();









}
