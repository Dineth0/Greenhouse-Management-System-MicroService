package lk.ijse.cropinventoryservice.service.impl;

import lk.ijse.cropinventoryservice.entity.CropBatch;
import lk.ijse.cropinventoryservice.entity.CropStatus;
import lk.ijse.cropinventoryservice.repo.CropRepo;
import lk.ijse.cropinventoryservice.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropRepo cropRepo;

    @Override
    public CropBatch registerBatch(CropBatch cropBatch) {
        cropBatch.setCropStatus(CropStatus.SEEDLING);
        return cropRepo.save(cropBatch);
    }
}
