package main.flight;

import java.util.Random;

public enum City {
    KYIV("Kyiv"),
    LVIV("Lviv"),
    ODESSA("Odessa"),
    LAS_VEGAS("Las-Vegas"),
    NEW_YORK("New-York"),
    PARIS("Paris"),
    MIAMI("Miami"),
    LONDON("London"),
    TORONTO("Toronto"),
    CAIRO("Cairo"),
    DUBAI("Dubai"),
    SEOUL("Seoul"),
    TOKYO("Tokyo"),
    ISTANBUL("Istanbul"),
    AMSTERDAM("Amsterdam"),
    BERLIN("Berlin"),
    MADRID("Madrid"),
    BALI("Bali"),
    MALDIVES("Maldives"),
    BOSTON("Boston"),
    TEL_AVIV("Tel-Aviv"),
    TALLINN("Tallinn"),
    WARSAW("Warsaw"),
    PRAGUE("Prague");

    private static final Random RND = new Random();
    private final String city;

    City(String city) {
        this.city = city;
    }

    public static City randomCity() {
        City[] cities = values();
        return cities[RND.nextInt(cities.length)];
    }

    public static City randomCity(City origin) {
        City[] cities = values();
        City destination = cities[RND.nextInt(cities.length)];
        while (destination.equals(origin)) {
            destination = cities[RND.nextInt(cities.length)];
        }
        return destination;
    }



    public String getCityName() {
        return this.city;
    }
}
