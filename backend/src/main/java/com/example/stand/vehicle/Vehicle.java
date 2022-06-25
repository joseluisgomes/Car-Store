package com.example.stand.vehicle;

import com.example.stand.Fuel;
import com.example.stand.Motor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Vehicle")
@Table(name = "Vehicles")
public class Vehicle implements Cloneable {
    @Id
    @SequenceGenerator(
            name = "vehicle_id_generator",
            sequenceName = "vehicle_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicle_id_generator"
    )
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
        private String brand, registration;
        private int seats;
        private Motor motor;
        private Fuel fuel;

        // Optional parameters
        private String color, model;
        private LocalDate fabricDate;

        public Builder(String brand,
                       String registration,
                       String model,
                       int seats,
                       Motor motor,
                       Fuel fuel) {
            this.brand = brand;
            this.registration = registration;
            this.model = model;
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

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
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
