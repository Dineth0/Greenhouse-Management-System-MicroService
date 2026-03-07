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

    @Override
    public void loginUser() {
        IOTAuthRequestDTO authRequestDTO = new IOTAuthRequestDTO("Dineth", "12345");
         IOTAuthResponseDTO response = webClient.post()
                .uri("/auth/login")
                .bodyValue(authRequestDTO)
                .retrieve()
                .bodyToMono(IOTAuthResponseDTO.class)
                .block();
        if(response != null) {
            this.token = response.getAccessToken();
        }
    }

    @Override
    public SensorDataDTO fetchSensorData() {
        if(this.token == null) {
            loginUser();
        }
        try{
            SensorDataDTO response = webClient.get()
                    .uri(baseUrl + "/sensors")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToMono(SensorDataDTO.class)
                    .block();
            sensorDataDTO = response;
            return response;
        }catch (Exception e){
            this.token = null;
            return null;
        }

    }

    @Override
    public SensorDataDTO getLatestFetch() {
        return sensorDataDTO;
    }
}
