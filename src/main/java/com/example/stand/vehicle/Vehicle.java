package com.example.stand.vehicle;

import com.example.stand.vehicle.engine.Fuel;
import com.example.stand.vehicle.engine.Motor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Vehicle")
@Table(name = "Vehicles")
public class Vehicle implements Cloneable {
    @Id
    @Column(unique = true, updatable = false)
    private Long id; // Primary key
    @Column(updatable = false)
    private String brand;
    private String model;
    @Column(unique = true)
    private String registration;
    @Column(updatable = false)
    private int seats; // Number of seats
    private String color;
    private Motor motor;
    private Fuel fuel;
    @Column(updatable = false)
    private LocalDate fabricDate; // Year of fabric

    // Build pattern for inheritance
    public abstract static class Builder<T extends Builder<T>> {
        // Required parameters
        private final Long id;
        private final String brand;
        private final String registration;
        private final int seats;
        private final Motor motor;
        private final Fuel fuel;

        // Optional parameters
        private String color, model;
        private LocalDate fabricDate;

        public Builder(Long id,
                       String brand,
                       String registration,
                       int seats,
                       Motor motor,
                       Fuel fuel) {
            this.id = id;
            this.brand = brand;
            this.registration = registration;
            this.seats = seats;
            this.motor = motor;
            this.fuel = fuel;
        }

        public Builder color(String val) { color = val; return this; }

        public Builder model(String val) { model = val; return this; }

        public Builder fabricDate(LocalDate val) { fabricDate = val; return this; }

        public Vehicle build() { return new Vehicle(this); }
    }

    public Vehicle() { }

    public Vehicle(Builder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.model = builder.model;
        this.registration = builder.registration;
        this.seats = builder.seats;
        this.color = builder.color;
        this.motor = builder.motor;
        this.fuel = builder.fuel;
        this.fabricDate = builder.fabricDate;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getRegistration() {
        return registration;
    }

    public int getSeats() {
        return seats;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public Motor getMotor() {
        return motor;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public LocalDate getFabricDate() {
        return fabricDate;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Vehicle vehicle))
            return false;
        return id.equals(vehicle.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", registration='" + registration + '\'' +
                ", seats=" + seats +
                ", color='" + color + '\'' +
                ", fuel=" + fuel +
                ", fabricDate=" + fabricDate +
                '}';
    }

    @Override
    public Vehicle clone() {
        try {
            final var vehicle = (Vehicle) super.clone();
            vehicle.id = this.id;
            vehicle.brand = this.brand;
            vehicle.model = this.model;
            vehicle.registration = this.registration;
            vehicle.seats = this.seats;
            vehicle.color = this.color;
            vehicle.fuel = this.fuel;
            vehicle.fabricDate = this.fabricDate;
            return vehicle;
        } catch (CloneNotSupportedException e) { throw new AssertionError(); }
    }
}
