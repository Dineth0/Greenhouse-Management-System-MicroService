package lk.ijse.cropinventoryservice.service.impl;

import lk.ijse.cropinventoryservice.entity.CropBatch;
import lk.ijse.cropinventoryservice.entity.CropStatus;
import lk.ijse.cropinventoryservice.repo.CropRepo;
import lk.ijse.cropinventoryservice.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropRepo cropRepo;

    @Override
    public CropBatch registerBatch(CropBatch cropBatch) {
        cropBatch.setCropStatus(CropStatus.SEEDLING);
        return cropRepo.save(cropBatch);
    }

    @Override
    public CropBatch updateStatus(Long id, CropStatus cropStatus) {
        CropBatch crop = cropRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Batch not found"));
        crop.setCropStatus(cropStatus);
        return cropRepo.save(crop);
    }

    @Override
    public List<CropBatch> getAllBatches() {
        return cropRepo.findAll();
    }
}
