package main;

import main.flight.Flight;
import main.flight.FlightController;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FlightController fc = new FlightController();
        fc.displayAllFlights();
    }
}
