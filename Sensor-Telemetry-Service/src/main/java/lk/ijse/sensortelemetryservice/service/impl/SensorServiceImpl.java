package lk.ijse.sensortelemetryservice.service.impl;

import lk.ijse.sensortelemetryservice.dto.IOTAuthRequestDTO;
import lk.ijse.sensortelemetryservice.dto.IOTAuthResponseDTO;
import lk.ijse.sensortelemetryservice.dto.SensorDataDTO;
import lk.ijse.sensortelemetryservice.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final WebClient webClient;
    private SensorDataDTO sensorDataDTO;
    private String token;


    @Value("${iot.api.base-url}")
    private String baseUrl;

    private String deviceId = "b751b8c9-644a-484c-ba3f-be63f9b27ad0";

    @Override
    public void loginUser() {
        IOTAuthRequestDTO authRequestDTO = new IOTAuthRequestDTO("Dineth", "12345");
         IOTAuthResponseDTO response = webClient.post()
                .uri("/auth/login")
                .bodyValue(authRequestDTO)
                .retrieve()
                .bodyToMono(IOTAuthResponseDTO.class)
                .block();
        System.out.println("LOGIN RESPONSE: " + response);
        if(response != null) {
            this.token = response.getAccessToken();
        }
    }

    @Override
    public SensorDataDTO fetchSensorData() {

        if(this.token == null){
            loginUser();
        }

        try {

            SensorDataDTO response = webClient.get()
                    .uri("/devices/telemetry/" + deviceId)
                    .header("Authorization","Bearer " + token)
                    .retrieve()
                    .bodyToMono(SensorDataDTO.class)
                    .block();
            System.out.println("TELEMETRY RESPONSE: " + response);

            this.sensorDataDTO = response;

            return response;

        } catch (Exception e) {

            System.out.println("Error fetching sensor data: " + e.getMessage());

            this.token = null;

            return null;
        }
    }

    @Override
    public SensorDataDTO getLatestFetch() {
        return sensorDataDTO;
    }
}
