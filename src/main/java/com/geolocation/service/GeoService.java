package com.geolocation.service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.cache.annotation.Cacheable;

import java.io.InputStream;
import java.io.IOException;
import java.net.InetAddress;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import com.geolocation.model.LocationDetails;
import com.geolocation.model.ErrorResponse;


@Service
public class GeoService {

	@Cacheable(value = "locationCache", key = "#request.getHeader('X-FORWARDED-FOR') ?: #request.getRemoteAddr()")
	public ResponseEntity<?> findMyLocation(HttpServletRequest request) {

		try(DatabaseReader dbReader = createDatabaseReader()) {

			String remoteIpAddress = request.getHeader("X-FORWARDED-FOR");

			if (remoteIpAddress == null || remoteIpAddress.isEmpty()) {
	            remoteIpAddress = request.getRemoteAddr();
	        }

			InetAddress ipAddress = InetAddress.getByName("128.101.101.101");
			CityResponse response = dbReader.city(ipAddress);
			Country country = response.getCountry();

			LocationDetails locationDetails = new LocationDetails(
	            country.getName(),
	            country.getIsoCode(),
	            response.getCity().getName(),
	            response.getLeastSpecificSubdivision().getName(),
	            response.getPostal().getCode()
        	);
	        
			return ResponseEntity.ok(locationDetails);

		}
		catch(IOException | GeoIp2Exception e) {

			e.printStackTrace();

			ErrorResponse errorResponse = new ErrorResponse(
	            "Error occurred while processing the request",
	            HttpStatus.INTERNAL_SERVER_ERROR.value(),
	            e.getMessage()
        	);

        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        		.body(errorResponse);

		}

	}

	private DatabaseReader createDatabaseReader() throws IOException {

	    try (InputStream inputStream = new ClassPathResource("db/GeoLite2-City.mmdb").getInputStream()) {
            return new DatabaseReader.Builder(inputStream).build();
        }

	}

}