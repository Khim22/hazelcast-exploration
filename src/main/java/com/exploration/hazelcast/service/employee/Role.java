package com.exploration.hazelcast.service.employee;

public enum Role {
    Member("Team Member"),
    Lead("Team Lead"),
    Manager("Manager"),
    Head("Head of Department");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
