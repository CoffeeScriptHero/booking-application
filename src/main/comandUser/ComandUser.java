package main.comandUser;

import main.booking.Booking;
import main.booking.BookingController;
import main.flight.City;
import main.flight.Flight;
import main.flight.FlightController;

import java.time.LocalDate;
import java.util.*;

public class ComandUser {
    Scanner scannerNum = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);
    FlightController flightController = new FlightController();
    BookingController bookingController = new BookingController();

    public ComandUser() {

        while (true) {

            showTask();

            System.out.println("Enter a number from 0 to 5:");
            int numUser = scannerNumUser();

            if (numUser == 0) {
                ArrayList saveBooking = (ArrayList) bookingController.getBookingList();
                bookingController.saveData(saveBooking);
                break;
            }

            switch (numUser) {
                case 1 -> {
                    onlineScoreboard();
                }
                case 2 -> {
                    showFlightInformation();
                }
                case 3 -> {
                    flightSearchAndBooking();
                }
                case 4 -> {
                    cancelTheReservation();
                }
                case 5 -> {
                    myFlights();
                }
                default -> System.out.println("Incorrect data. Try again");
            }
        }
    }
    public int scannerNumUser(){
        int number;
        do {
            while (!scannerNum.hasNextInt()) {
                System.out.println("It's not a number. Enter a number:");
                scannerNum.next();
            }
            number = scannerNum.nextInt();
        } while (number < 0 || number > 6);
        return number;
    }
    public int scannerNumUserOperation(){
        int number;
        do {
            while (!scannerNum.hasNextInt()) {
                System.out.println("It's not a number. Enter a number:");
                scannerNum.next();
            }
            number = scannerNum.nextInt();
        } while (number < 0);
        return number;
    }

    public String scannerStrUser() {
        while (!(scannerStr.hasNext())){
            System.out.println("Incorrect data. Try again:");
            scannerStr.next();
        }
        String vowel = scannerStr.next();
        return vowel;
    }

    public void showTask(){
        System.out.println("""
                
                - 1. Online scoreboard
                - 2. Show flight information
                - 3. Flight search and booking
                - 4. Cancel the reservation
                - 5. My flights
                - 0. Exit""");
    }

    public void onlineScoreboard(){
        flightController.displayAllFlights();
    }

    public void showFlightInformation(){
        System.out.println("Please, enter id flight:");
        int id = scannerNumUserOperation();
        flightController.getFlight(id).ifPresentOrElse(
                Flight::prettyFormat,
                () -> System.out.println("No flight, please try again:"));
    }

    public void flightSearchAndBooking(){
        System.out.println("Please, enter your destination:");
        String destination = scannerStrUser();
        System.out.println("Please, enter date (in format yyyy-mm-dd):");
        String date = scannerStrUser();
        LocalDate dateUser = LocalDate.parse(date);
        System.out.println("Please, enter number of persons:");
        int numberOfPerson = scannerNumUserOperation();
        ArrayList<Flight> f = flightController.findAvailableFlights(City.valueOf(destination.toUpperCase()), dateUser, numberOfPerson);
        flightController.displayFlights(f);
        //Якщо не знайдено помилка
        System.out.println("\n" +
                "Enter the ID of the flight you are interested in (to exit to the main menu, press 0):");
        int numUserOperation3 = scannerNumUserOperation();
        if(numUserOperation3 == 0) {
            return;
        } else {
            flightController.getFlight(numUserOperation3).ifPresentOrElse(
                    (flight) -> {flight.subtractAvailableSeats(numberOfPerson);for (int i = 0; i < numberOfPerson; i++) {
                        System.out.println("Enter name:");
                        String name = scannerStrUser();
                        System.out.println("Enter surname:");
                        String surname = scannerStrUser();
                        bookingController.makeBooking(flight, name, surname);
                        bookingController.saveData((ArrayList<Booking>) bookingController.getMyBookings(name, surname));

                    }},
                    () -> System.out.println("There is no such flight")
            );
        }
    }

    public void cancelTheReservation(){
        System.out.println("Enter the ID of the booking:");
        int idCanceled = scannerNumUserOperation();
        bookingController.cancelBooking(idCanceled);

    }

    public void myFlights(){
        System.out.println("Enter name:");
        String name = scannerStrUser();
        System.out.println("Enter surname:");
        //Якщо немає бронювань показати
        String surname = scannerStrUser();
        System.out.println(bookingController.getMyBookings(name, surname));
    }
}
