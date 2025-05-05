package dev.chiedo.weathermcpserver.service;

import dev.chiedo.weathermcpserver.model.WeatherDataResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(WeatherService.class)
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    void getWeatherDetailsByLocationWorks() {
        String location = "Nairobi";
        String encodedLocation = "Nairobi";

        String url = "https://wttr.in/" + encodedLocation + "?format=j1";

        String sampleResponse = """
                {
                  "current_condition": [
                    {
                      "temp_C": "25",
                      "temp_F": "77",
                      "humidity": "51",
                      "windspeedKmph": "9",
                      "weatherDesc": [
                        { "value": "Partly cloudy" }
                      ]
                    }
                  ]
                }
                """;

        server.expect(requestTo(url))
                .andRespond(withSuccess(sampleResponse, MediaType.APPLICATION_JSON));

        WeatherDataResponse response = weatherService.getWeatherDetailsByLocation(location);

        assertThat(response.location()).isEqualTo("Nairobi");
        assertThat(response.tempCelsius()).isEqualTo("25");
        assertThat(response.tempFahrenheit()).isEqualTo("77");
        assertThat(response.humidity()).isEqualTo("51");
        assertThat(response.windSpeed()).isEqualTo("9");
        assertThat(response.weatherDescription()).isEqualTo("Partly cloudy");
    }
}

