package com.example.geocoder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeoPointService {

    private final WebClient webClient;

    @Value("${spring.key.id}")
    private String id;

    @Value("${spring.key.secret}")
    private String secret;

    public GeoPointService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://naveropenapi.apigw.ntruss.com/map-geocode/v2")
                .build();
    }

    public Mono<String> findGeoPoint() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/geocode")
                        .queryParam("query", "서울특별시")
                        .build())
                .header("X-NCP-APIGW-API-KEY-ID", id)
                .header("X-NCP-APIGW-API-KEY", secret)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}