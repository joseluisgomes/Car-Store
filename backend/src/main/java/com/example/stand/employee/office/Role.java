package com.example.stand.employee.office;

public enum Role {
    SECRETARY("ROLE_USER"),
    ADMINISTRATOR("ROLE_ADMIN");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
