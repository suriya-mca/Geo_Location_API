package com.geolocation.model;

public record ErrorResponse(
	String message,
    int errorCode,
    String details) {}