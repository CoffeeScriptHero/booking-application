package main.booking;

import main.flight.Flight;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Booking implements Serializable {

    Random random = new Random();

    private final int id;
    private final String name;
    private final String surname;
    private final Flight flight;

    public Booking(Flight flight, String name, String surname) {
        this.id = random.nextInt(1, 1000);
        this.flight = flight;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\nBooking id: " + id +
                "\nRace: " + flight.getId() +
                "\nFlight from " + flight.getOrigin() +
                " to " + flight.getDestination() +
                "\nDate " + flight.getDate() +
                ", Time " + flight.getTime() +
                "\nName " + name + " " + surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && Objects.equals(random, booking.random) && Objects.equals(name, booking.name) && Objects.equals(surname, booking.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }
}
