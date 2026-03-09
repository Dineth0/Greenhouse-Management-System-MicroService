package lk.ijse.sensortelemetryservice.schedular;

import lk.ijse.sensortelemetryservice.dto.SensorDataDTO;
import lk.ijse.sensortelemetryservice.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class SensorScheduler {
    private final SensorService sensorService;
    private final WebClient webClient;

    private final String url = "http://localhost:8083/api/automation/data";
    @Scheduled(fixedRate = 10000)
    public void schedule() {
        SensorDataDTO sensorDataDTO = sensorService.fetchSensorData();

        if(sensorDataDTO == null){
            System.out.println("Sensor data not available");
            return;
        }
        webClient.post()
                .uri(url)
                .bodyValue(sensorDataDTO)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
