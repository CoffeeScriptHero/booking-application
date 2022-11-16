package main.flight;

public enum City {
    KYIV("Київ"),
    LVIV("Львів"),
    ODESSA("Одеса"),
    LAS_VEGAS("Лас-Вегас"),
    NEW_YORK("Нью-Йорк"),
    PARIS("Париж"),
    MIAMI("Маямі"),
    LONDON("Лондон"),
    TORONTO("Торонто"),
    CAIRO("Каїр"),
    TOKYO("Токіо"),
    AMSTERDAM("Амстердам"),
    BERLIN("Берлін"),
    BOSTON("Бостон"),
    TEL_AVIV("Тель-Авів"),
    TALLINN("Таллінн"),
    WARSAW("Варшава"),
    PRAGUE("Прага");

    private final String city;

    City(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }
}
