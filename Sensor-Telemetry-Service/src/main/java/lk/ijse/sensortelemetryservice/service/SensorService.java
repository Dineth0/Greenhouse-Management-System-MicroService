package lk.ijse.sensortelemetryservice.service;

import lk.ijse.sensortelemetryservice.dto.IOTAuthRequestDTO;
import lk.ijse.sensortelemetryservice.dto.IOTAuthResponseDTO;
import lk.ijse.sensortelemetryservice.dto.SensorDataDTO;
import reactor.core.publisher.Mono;

public interface SensorService {
    public void loginUser();

        SensorDataDTO fetchSensorData();
    SensorDataDTO getLatestFetch();
}
