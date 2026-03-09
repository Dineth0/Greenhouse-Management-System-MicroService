package lk.ijse.automationcontrolservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensorDataDTO {
    private String deviceId;
    private String zoneId;
    private double temperature;
    private double humidity;
}
