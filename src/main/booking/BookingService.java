package main.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingDAO bookingDAO = new BookingDAO();
    Scanner scanner = new Scanner(System.in);

    public void makeBooking(String name, String surname){
        try {Booking newBooking = new Booking(name, surname);
            System.out.println("You made a successful booking: " + newBooking);
            bookingDAO.addBooking(newBooking);
        } catch (BookingException e){
            e.getBookingException("Something went wrong, please try again");
        }
    }

    public void cancelBooking(int id) {
        try {

            List<Integer> idList = bookingDAO.getBookingList().stream().
                    map(Booking::getId).
                    collect(Collectors.toList());

            for (Integer integer : idList) {
                if (integer == id) {
                    bookingDAO.cancelBooking(id);
                }
            }
        } catch (BookingException e){
            e.getBookingException("Can`t cancel this booking, please try again");
        }
    }

    public List<Booking> getMyBookings(String name, String surname){
        return bookingDAO.getBookingList()
                .stream()
                .filter(booking -> booking.getName().equals(name) && booking.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    public void saveData(ArrayList<Booking> bookingList){
        bookingDAO.saveData(bookingList);
    }

    public ArrayList<Booking> getData() {
        return bookingDAO.getData();
    }

}
