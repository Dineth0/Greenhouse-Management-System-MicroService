package lk.ijse.automationcontrolservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZoneThresholdDTO {
    private Long zoneId;
    private double minTemp;
    private double maxTemp;
}
