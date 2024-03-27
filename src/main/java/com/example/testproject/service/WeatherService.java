package com.example.testproject.service;

import com.example.testproject.model.messageA.MsA;
import com.example.testproject.model.weather.Weather;

public interface WeatherService {

    Weather getWeatherData(MsA msA);

    double getAirTemperature(MsA msA);
}
