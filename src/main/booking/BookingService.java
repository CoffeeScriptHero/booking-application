package main.booking;

import main.flight.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingDAO bookingDAO;

    public BookingService() {
        this.bookingDAO = new CollectionBookingDAO();
    }

    public Booking makeBooking(Flight flight, String name, String surname){
       Booking newBooking = new Booking(flight, name, surname);
            System.out.println("You made a successful booking: " + newBooking);
            bookingDAO.addBooking(newBooking);
            return newBooking;
    }

    public void cancelBooking(int id) {
        try {

            List<Integer> idList = bookingDAO.getBookingList().stream().
                    map(Booking::getId).
                    collect(Collectors.toList());

            for (Integer integer : idList) {
                if (integer == id) {
                    bookingDAO.cancelBooking(id);
                    System.out.println("Your booking #" + id + " successfully canceled");
                }
            }
        } catch (BookingException e){
            e.getBookingException("Can`t cancel this booking, please try again");
        }
    }

    public ArrayList<Booking> getMyBookings(String name, String surname){
        return (ArrayList<Booking>) bookingDAO.getBookingList()
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

    public ArrayList<Booking> getBookingList(){
        return bookingDAO.getBookingList();
    }

}
