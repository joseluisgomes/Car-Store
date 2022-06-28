package com.example.stand.vehicle.engine;

public enum Fuel {
    GASOLINE("Gasoline"), DIESEL("Diesel"),
    GAS("Gas"), ELECTRIC("Electric");

    private final String fuel;

    Fuel(String fuel) { this.fuel = fuel; }

    public String getFuel() { return fuel; }
}
