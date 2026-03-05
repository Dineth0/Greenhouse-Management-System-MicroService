package lk.ijse.zonemanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceResponseDTO {
    private String deviceId;
    private String name;
    private String zoneId;
    private String userId;
    private String createdAt;
}
