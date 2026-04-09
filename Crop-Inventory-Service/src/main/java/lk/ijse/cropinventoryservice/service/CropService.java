package lk.ijse.cropinventoryservice.service;

import lk.ijse.cropinventoryservice.entity.CropBatch;
import lk.ijse.cropinventoryservice.entity.CropStatus;

public interface CropService {
    CropBatch registerBatch(CropBatch cropBatch);
    CropBatch updateStatus(Long id, CropStatus cropStatus);
}
