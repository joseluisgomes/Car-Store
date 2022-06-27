package com.example.stand;

public enum Motor {
    CAR("CAR"), JEEP("JEEP"),
    VAN("VAN"), MOTORCYCLE("MOTORCYCLE"),
    CAMPER_VAN("CAMPER_VAN");

    private final String motor;

    Motor(String motor) { this.motor = motor; }

    public String getMotor() { return motor; }
}
