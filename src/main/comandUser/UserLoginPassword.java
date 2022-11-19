package main.comandUser;

import main.booking.Booking;
import main.booking.BookingController;
import main.flight.Flight;
import main.logger.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserLoginPassword {
    private String login;
    private String password;
    public List<Booking> getBooking() {
        return booking;
    }
    private List<Booking> booking;
    private final String AUTHORIZATION = "src/main/database/LoginPassword.txt";
    private ArrayList<UserLoginPassword> userLoginPasswords = loadUserLoginPasswords();
    BookingController userBooking = new BookingController();

//    public UserLoginPassword(String login, String password){
//        this.login = login;
//        this.password = password;
//    }
    public UserLoginPassword(String login, String password, List<Booking> booking){
        this.login = login;
        this.password = password;
        this.booking = booking;
    }
    public UserLoginPassword(){}

    public void saveLoginPassword(ArrayList<UserLoginPassword> userLoginPasswords) {
        try (
                FileOutputStream fos = new FileOutputStream(AUTHORIZATION);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            for (UserLoginPassword userLoginPassword : userLoginPasswords) {
                oos.writeObject(userLoginPassword);
            }
            Logger.info("LoginPassword are saved to a file");
        } catch (IOException exception) {
            Logger.error("LoginPassword are not saved to a file");
        }
    }

    public ArrayList<UserLoginPassword> loadUserLoginPasswords() {
        ArrayList<UserLoginPassword> userLoginPasswordsFromFile = new ArrayList<>();
        try (
                FileInputStream fis = new FileInputStream(AUTHORIZATION);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            while (fis.available() > 0) {
                userLoginPasswordsFromFile.add((UserLoginPassword) ois.readObject());
            }
            Logger.info("Not correct Login or Password");
            this.userLoginPasswords = userLoginPasswordsFromFile;
        } catch (IOException | ClassNotFoundException exception) {
            Logger.error("Error");

        }
        return userLoginPasswordsFromFile;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
