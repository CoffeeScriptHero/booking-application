package main.booking;

import main.logger.Logger;

import java.io.*;
import java.util.ArrayList;
public class CollectionBookingDAO implements BookingDAO {

    private ArrayList<Booking> bookingList;

    File bookingBase = new File("bookingData.txt");

    public CollectionBookingDAO() {
        if (getData().isEmpty()){
            this.bookingList = new ArrayList<>();
        }
        else {
            this.bookingList = getData();
        }
    }
    public void clearDatabase() {
        saveData(new ArrayList<>());
    }


    @Override
    public ArrayList<Booking> getBookingList(){
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
            Logger.info("Bookings are saved to file");
        } catch (IOException exception) {
            Logger.error("Something went wrong! Bookings are not saved to file");
        }
    }

    @Override
    public ArrayList<Booking> getData() {
        ArrayList<Booking> bookingListFromBase = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(bookingBase);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                bookingListFromBase.add((Booking) ois.readObject());
            } Logger.info("Bookings were loaded from file");
            this.bookingList = bookingListFromBase;
        } catch (IOException | ClassNotFoundException exception) {
            Logger.info("Something went wrong, bookings were not loaded from file");
        }
        return bookingListFromBase;
    }

}
