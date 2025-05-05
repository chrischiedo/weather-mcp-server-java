package dev.chiedo.weathermcpserver.model;

public record WeatherDataResponse(
        String location,
        String weatherDescription,
        String tempCelsius,
        String tempFahrenheit,
        String humidity,
        String windSpeed
) {}
