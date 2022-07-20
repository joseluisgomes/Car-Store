package com.example.stand.vehicle.engine;

public enum Motor {
    CAR("Car"), JEEP("Jeep"),
    VAN("Van"), MOTORCYCLE("Motorcycle"),
    CAMPER_VAN("Camper Van");

    private final String motor;

    Motor(String motor) { this.motor = motor; }

    public String getMotor() { return motor; }
}
