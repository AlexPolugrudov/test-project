package com.example.testproject.service;

import com.example.testproject.exception.UnauthorizedException;
import com.example.testproject.exception.UnavailableWeatherServiceException;
import com.example.testproject.model.messageA.Coordinates;
import com.example.testproject.model.messageA.MsA;
import com.example.testproject.model.weather.Weather;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class GismeteoWeatherService implements WeatherService {

    @Value("${gismeteo.token}")
    private String gismeteoToken;
    @Value("${gismeteo.url}")
    private String gismeteoUrl;

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
                .uri(gismeteoUrl + "/current/?", uriBuilder ->
                        uriBuilder
                                .queryParam("latitude", coordinates.getLatitude())
                                .queryParam("longitude", coordinates.getLongitude())
                                .build())
                .header("X-Gismeteo-Token", gismeteoToken)
                .retrieve()
                .onStatus(HttpStatus.UNAUTHORIZED::equals, clientResponse -> {
                    log.error("Unauthorized access to weather service");
                    return Mono.error(new UnauthorizedException("Unauthorized access to weather service"));
                })
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
