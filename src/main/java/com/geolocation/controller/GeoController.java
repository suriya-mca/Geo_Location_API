package com.geolocation.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import com.geolocation.service.GeoService;
import com.geolocation.model.LocationDetails;

@RestController
@RequestMapping(path = "/api/v1", produces = "application/json")
public class GeoController {

	private final GeoService geoService;

    public GeoController (GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/findMyLocation") 
    public ResponseEntity<?> logoutUser (HttpServletRequest request)  {

        return geoService.findMyLocation(request);

    }

}