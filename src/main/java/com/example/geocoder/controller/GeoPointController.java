package com.example.geocoder.controller;

import com.example.geocoder.service.GeoPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class GeoPointController {

    private final GeoPointService geoPointService;

    @Autowired
    public GeoPointController(GeoPointService geoPointService) {
        this.geoPointService = geoPointService;
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getGeoPoint() {
        return geoPointService.findGeoPoint()
                .map(response -> ResponseEntity.ok().body(response))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
