package com.geolocation.service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.exception.GeoIp2Exception;


@Service
public class GeoService {

	public String findMyLocation(HttpServletRequest request) {

		try {

			String remoteIpAddress = request.getHeader("X-FORWARDED-FOR");

			if (remoteIpAddress == null || remoteIpAddress.isEmpty()) {
	            remoteIpAddress = request.getRemoteAddr();
	        }

			Resource geoLiteResourceFile = new ClassPathResource("db/GeoLite2-City.mmdb");
			File loadDatabase = geoLiteResourceFile.getFile();
			DatabaseReader dbReader = new DatabaseReader.Builder(loadDatabase).build();
			InetAddress ipAddress = InetAddress.getByName("128.101.101.101");
			CityResponse response = dbReader.city(ipAddress);

			Country getCountryName = response.getCountry();
			String getCountryIsoCode = getCountryName.getIsoCode();
	        
			return getCountryIsoCode;

		}
		catch(IOException | GeoIp2Exception e) {

			e.printStackTrace();
			return "Error occurred while processing the request";

		}

	}

}