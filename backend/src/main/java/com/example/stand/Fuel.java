package com.example.stand;

public enum Fuel {
    GASOLINE("Gasoline"), DIESEL("Diesel"),
    GAS("GAS"), ELECTRIC("Electric");

    private final String fuel;

    Fuel(String fuel) { this.fuel = fuel; }

    public String getFuel() { return fuel; }
}
