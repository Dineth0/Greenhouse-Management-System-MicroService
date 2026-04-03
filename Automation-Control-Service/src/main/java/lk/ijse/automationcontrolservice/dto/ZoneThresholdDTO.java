package lk.ijse.automationcontrolservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZoneThresholdDTO {
    private String zoneId;
    private double minTemp;
    private double maxTemp;
}
