package dev.chiedo.weathermcpserver;

import dev.chiedo.weathermcpserver.service.WeatherService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherMcpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherMcpServerApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider weatherTool(WeatherService weatherService) {
		return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
	}
}
