package dev.chiedo.weathermcpserver;

import dev.chiedo.weathermcpserver.model.WeatherDataResponse;
import dev.chiedo.weathermcpserver.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WeatherMcpServerApplicationTests {

	@Autowired
	private WeatherService weatherService;

	@Test
	void contextLoads() {
	}

	@Test
	void getWeatherDetailsByLocationReturnsLiveData() {
		String location = "Nairobi";

		WeatherDataResponse response = weatherService.getWeatherDetailsByLocation(location);

		// Basic assertions to confirm live data mapping
		assertThat(response).isNotNull();
		assertThat(response.location()).isEqualTo(location);
		assertThat(response.tempCelsius()).isNotBlank();
		assertThat(response.tempFahrenheit()).isNotBlank();
		assertThat(response.weatherDescription()).isNotBlank();
		assertThat(response.humidity()).isNotBlank();
		assertThat(response.windSpeed()).isNotBlank();

		System.out.println("Live weather data response: " + response);
	}

}
