package lk.ijse.cropinventoryservice.controller;

import lk.ijse.cropinventoryservice.dto.ResponseDTO;
import lk.ijse.cropinventoryservice.entity.CropBatch;
import lk.ijse.cropinventoryservice.entity.CropStatus;
import lk.ijse.cropinventoryservice.service.CropService;
import lk.ijse.cropinventoryservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crop")
@RequiredArgsConstructor
@CrossOrigin
public class CropController {

    private final CropService cropService;

    @PostMapping
    public ResponseEntity<ResponseDTO> registerCrop(@RequestBody CropBatch cropBatch) {
        try {
            CropBatch creatCropBatch = cropService.registerBatch(cropBatch);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created, "Crop Batch Created", creatCropBatch));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseDTO> updateStatus(@PathVariable Long id, @RequestParam CropStatus status) {
        CropBatch updateCropBatch = cropService.updateStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "success", updateCropBatch));
    }
}
