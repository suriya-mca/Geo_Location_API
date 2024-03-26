package com.geolocation.service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;


@Service
public class GeoService {

	public String findMyLocation(HttpServletRequest request) {

		String remoteIpAddress = request.getHeader("X-FORWARDED-FOR");

		if (remoteIpAddress == null || remoteIpAddress.isEmpty()) {
            remoteIpAddress = request.getRemoteAddr();
        }
        
		return remoteIpAddress;

	}

}