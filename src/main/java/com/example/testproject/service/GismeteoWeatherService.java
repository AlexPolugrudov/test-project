package com.example.testproject.service;

import com.example.testproject.exception.UnavailableWeatherServiceException;
import com.example.testproject.model.messageA.Coordinates;
import com.example.testproject.model.messageA.MsA;
import com.example.testproject.model.weather.Weather;
import com.example.testproject.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class GismeteoWeatherService implements WeatherService {

    @Value("${gismeteo.token}")
    private String gismeteoToken;

    private final WebClient.Builder webClientBuilder;

    @Override
    public Weather getWeatherData(MsA msA) {
        return getWeatherDataByCoordinates(msA.getCoordinates());
    }

    @Override
    public double getAirTemperature(MsA msA) {
        var weather = getWeatherDataByCoordinates(msA.getCoordinates());
        return weather.getTemperature().getAir().getC();
    }

    private Weather getWeatherDataByCoordinates(Coordinates coordinates) {
        var weather = webClientBuilder.build().get()
                .uri(Utility.SERVICE_GISMETEO_URL + "/current/", uriBuilder ->
                        uriBuilder
                                .queryParam("latitude", coordinates.getLatitude())
                                .queryParam("longitude", coordinates.getLongitude())
                                .build())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(gismeteoToken))
                .retrieve()
                .bodyToMono(Weather.class)
                .onErrorResume(throwable -> {
                    log.error("Weather service is unavailable");
                    return Mono.empty();
                })
                .block();

        if (weather == null) {
            throw new UnavailableWeatherServiceException("Weather service is unavailable");
        }

        return weather;
    }
}
