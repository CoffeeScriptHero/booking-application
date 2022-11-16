package main.flight;

import main.styles.TextStyle;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;

public class Flight implements Serializable {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private City origin;
    private City destination;
    private int availableSeats;

    public Flight(LocalDate date, LocalTime time, City origin, City destination, int availableSeats) {
        this.id = this.generateId();
        this.date = date;
        this.time = time;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    private int generateId() {
        // returns a random id in range [1, 10000]
        return new Random().nextInt(1, 10001);
    }

    public void addAvailableSeats(int number) {
        this.availableSeats += number;
    }

    public void subtractAvailableSeats(int number) {
        this.availableSeats -= number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void prettyFormat() {
        String title = "------------------- Flight -------------------";
        String end = "----------------------------------------------";
        String bold = TextStyle.BLACK_BOLD.getStyle();
        String reset = TextStyle.RESET.getStyle();
        String spaces = availableSeats >= 10 ? "   " : "    ";
        System.out.printf("%s\n\tFrom: %s%s%s\n\tTo:   %s%s%s\n\tDate: %s\t  Time: %s\n\tAvailable seats: %d%sFlight id: %d\n%s\n",
                title, bold, origin.getCityName(), reset, bold, destination.getCityName(), reset, date, time,
                availableSeats, spaces, id, end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && Objects.equals(date, flight.date) && Objects.equals(time, flight.time)
                && origin == flight.origin && destination == flight.destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time, origin, destination);
    }

    @Override
    public String toString() {
        return String.format("Flight{id=%d, date=%s, time=%s, origin=%s," +
                " destination=%s, availableSeats=%d}", id, date, time, origin, destination, availableSeats);
    }
}
