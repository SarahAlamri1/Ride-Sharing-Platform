package com.customer_service.customer_service.dto;

public enum RideStatus {
    WAITING("WAITING"),
    ACCEPTED("ACCEPTED");

    private final String value;

    RideStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

