package lk.ijse.sensortelemetryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IOTAuthRequestDTO {
    private String username;
    private String password;
}
