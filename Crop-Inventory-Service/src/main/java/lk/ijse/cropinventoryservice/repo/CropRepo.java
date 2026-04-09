package lk.ijse.cropinventoryservice.repo;

import lk.ijse.cropinventoryservice.entity.CropBatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepo extends JpaRepository<CropBatch, Long> {
}
