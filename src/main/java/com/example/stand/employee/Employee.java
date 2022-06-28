package com.example.stand.employee;

import com.example.stand.vehicle.Vehicle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "Employee")
@Table(name = "Employees")
public class Employee {
    @Id
    @Column(unique = true, updatable = false)
    private Long id;
    private String name;
    @Column(unique = true, updatable = false)
    private String email;
    private String jobTitle;
    @Column(unique = true, updatable = false)
    private String phone;
    private String imageUrl;

    // Build pattern for inheritance
    public abstract static class Builder<T extends Vehicle.Builder<T>> {
        // Required parameters
        private final Long id;
        private final String name;
        private final String email;
        private final String phone;

        // Optional parameters
        private String jobTitle;
        private String imageUrl;

        public Builder(Long id,
                       String name,
                       String email,
                       String phone) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        public Builder jobTitle(String val) { jobTitle = val; return this;}
        public Builder imageUrl(String val) { imageUrl = val; return this;}
    }

    public Employee() { }

    public Employee(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.jobTitle = builder.jobTitle;
        this.phone = builder.phone;
        this.imageUrl = builder.imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Employee employee))
            return false;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public Employee clone() {
       try {
           final var employee = (Employee) super.clone();
           employee.id = this.id;
           employee.name = this.name;
           employee.email = this.email;
           employee.jobTitle = this.jobTitle;
           employee.phone = this.phone;
           employee.imageUrl = this.imageUrl;
           return employee;
       } catch (CloneNotSupportedException e) { throw new RuntimeException(e); }
    }
}
