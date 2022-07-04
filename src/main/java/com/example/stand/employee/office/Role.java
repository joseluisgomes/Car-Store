package com.example.stand.employee.office;

public enum Role {
    SECRETARY("SECRETARY"),
    ADMINISTRATOR("ADMINISTRATOR");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
