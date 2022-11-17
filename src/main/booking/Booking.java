package main.booking;

import main.flight.Flight;

import java.util.Objects;
import java.util.Random;

public class Booking {

    Random random = new Random();

    private final int id;
    private final String name;
    private final String surname;
    private final Flight flight;

    public Booking(Flight flight, String name, String surname) {
        this.id = (random.nextInt() * 9000) + 1000;
        this.flight = flight;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Booking { " +
                "id=" + id +
                ", flight=" + flight +
                ", passName='" + name +
                ", passSurname='" + surname +
                '}';
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
