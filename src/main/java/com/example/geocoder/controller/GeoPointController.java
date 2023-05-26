package com.example.geocoder.controller;

import com.example.geocoder.service.GeoPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GeoPointController {

    private final GeoPointService geoPointService;

    @GetMapping
    public Mono<ResponseEntity<String>> getGeoPoint() {
        return geoPointService.findGeoPoint()
                .map(response -> ResponseEntity.ok().body(response))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
