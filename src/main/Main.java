package main;

import main.flight.FlightController;

public class Main {
    public static void main(String[] args) {
        FlightController fc = new FlightController();
        fc.displayAllFlights();
    }
}