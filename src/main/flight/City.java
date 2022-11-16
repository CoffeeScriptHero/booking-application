package main.flight;

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

    private final String city;

    City(String city) {
        this.city = city;
    }

    public String getCityName() {
        return this.city;
    }
}
