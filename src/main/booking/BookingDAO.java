package main.booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    private final List <Booking> bookingList = new ArrayList<>();
    File bookingBase = new File("src/main/java/org/example/bookingData.txt");


    public List<Booking> getBookingList(){
        return bookingList;
    }

    public void addBooking(Booking newBooking){
        bookingList.add(newBooking);
    }

    public void cancelBooking(int id) {
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getId() == id) {
                bookingList.remove(bookingList.get(i));
            }
        }
    }

    public void saveData (ArrayList<Booking> bookingList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bookingBase))) {
            for (Booking booking : bookingList) {
                oos.writeObject(booking);
            }
        } catch (BookingException | IOException e) {
            System.out.println("Something went wrong, please try again");
        }
    }

    public ArrayList<Booking> getData() {
        ArrayList<Booking> bookingList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(bookingBase);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                bookingList.add((Booking) ois.readObject());
                bookingList.forEach(this::addBooking);
            }
        } catch (BookingException | IOException | ClassNotFoundException e) {
            System.out.println("Something went wrong, please try again");
        }
        return bookingList;
    }









}
