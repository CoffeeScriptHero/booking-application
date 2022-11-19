package main.exceptions;

public class AvailableSeatsException extends RuntimeException {
    private final int availableSeats;
    private final int passengersNumber;

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getPassengersNumber() {
        return passengersNumber;
    }

    public AvailableSeatsException(String message, int availableSeats, int passengersNumber) {
        super(message);
        this.availableSeats = availableSeats;
        this.passengersNumber = passengersNumber;
    }
}
