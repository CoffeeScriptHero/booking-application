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
    UserLoginPassword userLoginPassword = new UserLoginPassword();

    public ComandUser() {
    }

    public void run() {
        boolean audit = true;
        while (audit) {

            flightController.createFlights();

            showRegistration();
            int registrationTask = scannerNumUser();

            if (registrationTask == 0) {
                break;
            }

            switch (registrationTask) {
                case 1 -> {

                    if (auditRegistration() == null) {
                        return;
                    }

                    boolean logInSuccessful = true;
                    while (logInSuccessful) {

                        showTaskAuthorization();

                        System.out.println("Enter a number from 0 to 6:");
                        int numUser = scannerNumUser();

                        if (numUser == 0) {
                            ArrayList saveBooking = bookingController.getBookingList();
                            bookingController.saveData(saveBooking);
                            audit = false;
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
                                System.out.println(auditRegistration().getBooking());
                            }
                            case 6 -> {
                                logInSuccessful = false;
                            }
                            default -> System.out.println("Incorrect data. Try again");
                        }
                    }
                }
                case 2 -> {
                    while (true) {

                        showTask();

                        System.out.println("Enter a number from 0 to 5:");
                        int numUser = scannerNumUser();

                        if (numUser == 0) {
                            ArrayList saveBooking = bookingController.getBookingList();
                            bookingController.saveData(saveBooking);
                            audit = false;
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

            }
        }
    }

    public int scannerNumUser() {
        int number;
        do {
            while (!scannerNum.hasNextInt()) {
                System.out.println("It's not a number. Enter a number:");
                scannerNum.next();
            }
            number = scannerNum.nextInt();
        } while (number < 0 || number > 7);

        return number;
    }

    public int scannerNumUserOperation() {
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
        while (!(scannerStr.hasNext())) {
            System.out.println("Incorrect data. Try again:");
            scannerStr.next();
        }
        String vowel = scannerStr.next();
        return vowel;
    }

    public void showTask() {
        System.out.println("""
                                
                - 1. Online scoreboard
                - 2. Show flight information
                - 3. Flight search and booking
                - 4. Cancel the reservation
                - 5. My flights
                - 0. Exit""");
    }

    public void showTaskAuthorization() {
        System.out.println("""
                                
                - 1. Online scoreboard
                - 2. Show flight information
                - 3. Flight search and booking
                - 4. Cancel the reservation
                - 5. My flights
                - 6. End session
                - 0. Exit""");
    }

    public void onlineScoreboard() {
        flightController.displayUpcomingFlights();
    }

    public void showFlightInformation() {
        System.out.println("Please, enter id flight:");
        int id = scannerNumUserOperation();
        flightController.getFlight(id).ifPresentOrElse(
                Flight::prettyFormat,
                () -> System.out.println("No flight, please try again:"));
    }

    public void flightSearchAndBooking() {
        System.out.println("Please, enter your destination:");
        String destination = scannerStrUser();
        System.out.println("Please, enter date (in format yyyy-mm-dd):");
        String date = scannerStrUser();
        LocalDate dateUser = LocalDate.parse(date);
        System.out.println("Please, enter number of persons:");
        int numberOfPerson = scannerNumUserOperation();

        ArrayList<Flight> flights = flightController.findAvailableFlights(City.valueOf(destination.toUpperCase()), dateUser, numberOfPerson);
        if (flights.size() == 0) {
            System.out.println("Unfortunately, there are no available flights.");
        } else {
            flightController.displayFlights(flights);
            System.out.println("\n" +
                    "Enter the ID of the flight you are interested in (to exit to the main menu, press 0):");
            int numUserOperation3 = scannerNumUserOperation();
            if (numUserOperation3 == 0) {
                return;
            } else {
                flightController.getFlight(numUserOperation3).ifPresentOrElse(
                        (flight) -> {
                            try {
                                flight.subtractAvailableSeats(numberOfPerson);
                                flightController.updateDatabase();
                            } catch (Exception ex) {
                                return;
                            }
                            for (int i = 0; i < numberOfPerson; i++) {
                                System.out.println("Enter name:");
                                String name = scannerStrUser();
                                System.out.println("Enter surname:");
                                String surname = scannerStrUser();
                                doYouWantRegistration();
                                int registrationTask = scannerNumUser();
                                switch (registrationTask) {
                                    case 1 -> {
                                        System.out.println("Create login:");
                                        String login = scannerStrUser();
                                        System.out.println("Create password:");
                                        String password = scannerStrUser();
                                        bookingController.makeBooking(flight, name, surname);
                                        UserLoginPassword newUser = new UserLoginPassword(login, password, bookingController.getMyBookings(name, surname));
                                        ArrayList<UserLoginPassword> registration = new ArrayList<UserLoginPassword>();
                                        registration.add(newUser);
                                        newUser.saveLoginPassword(registration);
                                    }
                                    case 2 -> {
                                        bookingController.makeBooking(flight, name, surname);
                                        continue;
                                    }
                                }
                                bookingController.saveData((ArrayList<Booking>) bookingController.getMyBookings(name, surname));

                            }
                        },
                        () -> System.out.println("There is no such flight")
                );
            }
        }
    }

    public void cancelTheReservation() {
        System.out.println("Enter the ID of the booking:");
        int idCanceled = scannerNumUserOperation();
        bookingController.cancelBooking(idCanceled);

    }

    public void myFlights() {
        System.out.println("Enter name:");
        String name = scannerStrUser();
        System.out.println("Enter surname:");
        String surname = scannerStrUser();
        if (bookingController.getMyBookings(name, surname).size() == 0) {
            System.out.println("You don't have booking");
        } else {
            System.out.println(bookingController.getMyBookings(name, surname));
        }
    }

    public void showRegistration() {
        System.out.println("""
                
                
                Are you registered?
                - 1. Yes
                - 2. No
                - 0. Exit""");
    }

    public UserLoginPassword auditRegistration() {
        System.out.println("Enter login:");
        String login = scannerStrUser();
        System.out.println("Enter password:");
        String password = scannerStrUser();
        ArrayList<UserLoginPassword> audit = userLoginPassword.loadUserLoginPasswords();
        for (UserLoginPassword loginPassword : audit) {
            if (loginPassword.getLogin().equals(login) && loginPassword.getPassword().equals(password)) {
                System.out.println("Check passed. Launching the program...");
                return loginPassword;
            } else {
                System.out.println("Incorrect data. Restart the program");
                return null;
            }
        }
        return null;
    }

    public void doYouWantRegistration() {
        System.out.println("""
                                
                Do you want to register?
                - 1. Yes
                - 2. No""");
    }
}
