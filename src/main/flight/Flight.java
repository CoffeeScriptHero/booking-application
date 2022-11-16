package main.flight;

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

    public Flight(int id, LocalDate date, LocalTime time, City origin, City destination, int availableSeats) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

//    private int generateAvailableSeats() {
//        Random random = new Random();
//        return new Random().nextInt(5, 31);
//    }

//    private

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
        return "Flight{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", origin=" + origin +
                ", destination=" + destination +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
