package com.example.demo.enums;

public enum Roles {
    Admin,
    User;

    public String getRole() {
        return name();
    }
    public static Roles fromString(String role) {
        String normalizedRole = role.toUpperCase();
        return Roles.valueOf(normalizedRole);
    }
}
