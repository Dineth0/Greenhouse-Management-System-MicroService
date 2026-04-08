package lk.ijse.cropinventoryservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.ijse.cropinventoryservice.entity.CropStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CropDTO {
    private Long id;
    private String cropName;
    private int quantity;
    private CropStatus cropStatus;
    private String plantedDate;
}
