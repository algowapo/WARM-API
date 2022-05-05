package com.warm.exception;

public enum Error {

    INVALID_DATA(1001, "Invalid Data"),
    USER_NOT_FOUND(1002, "User not found :("),
    APPLIANCE_NOT_FOUND(1003, "Appliance not found :("),
    METER_NOT_FOUND(1004, "Meter not found :("),
    RESOURCE_NOT_FOUND(1005, "Resource not found :("),
    HISTORY_NOT_FOUND(1005, "History not found :("),
    ;


    private final int codError;

    private final String message;

    Error(int codError, String message) {
        this.codError = codError;
        this.message = message;
    }

    public int getCodError() {
        return this.codError;
    }

    public String getMessage() {
        return this.message;
    }


}

