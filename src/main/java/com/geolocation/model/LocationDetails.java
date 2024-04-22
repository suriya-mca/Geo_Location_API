package com.geolocation.model;

public record LocationDetails(
	String country, 
	String countryCode, 
	String city, 
	String state,
	String postal) {}
