package main.booking;

import java.util.ArrayList;

public interface BookingDAO {


    ArrayList<Booking> getBookingList();

    void addBooking(Booking booking);
    void cancelBooking(int id);

    void saveData(ArrayList<Booking> bookingList);

   ArrayList<Booking> getData();

    void clearDatabase();









}
