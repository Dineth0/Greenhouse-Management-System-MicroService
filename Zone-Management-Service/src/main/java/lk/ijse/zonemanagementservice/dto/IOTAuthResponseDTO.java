package lk.ijse.zonemanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IOTAuthResponseDTO {
    private String accessToken;
    private String refreshToken;
}
