package main.booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CollectionBookingDAO implements BookingDAO {
    private final ArrayList<Booking> bookingList = getData();
    File bookingBase = new File("src/main/booking/bookingData.txt");

    @Override
    public List<Booking> getBookingList(){
        return bookingList;
    }

    @Override
    public void addBooking(Booking newBooking){
        bookingList.add(newBooking);
    }


    @Override
    public void cancelBooking(int id) {
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getId() == id) {
                bookingList.remove(bookingList.get(i));
            }
        }
    }

    @Override
    public void saveData (ArrayList<Booking> bookingList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bookingBase))) {
            for (Booking booking : bookingList) {
                oos.writeObject(booking);
            }
        } catch (BookingException | IOException e) {
            System.out.println("Something went wrong, please try again");
        }
    }

    @Override
    public ArrayList<Booking> getData() {
        ArrayList<Booking> bookingListFromBase = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(bookingBase);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                bookingListFromBase.add((Booking) ois.readObject());
            }
        } catch (BookingException | IOException | ClassNotFoundException e) {
            System.out.println("Something went wrong, please try again");
        }
        return bookingListFromBase;
    }









}
