package lk.ijse.zonemanagementservice.controller;

import lk.ijse.zonemanagementservice.dto.ResponseDTO;
import lk.ijse.zonemanagementservice.entity.Zone;
import lk.ijse.zonemanagementservice.service.ZoneService;
import lk.ijse.zonemanagementservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zones")
@CrossOrigin
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createZone(@RequestBody Zone zone) {
        try {
            Zone createdZone = zoneService.createZone(zone);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created, "Zone Created", createdZone));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getZone(@PathVariable Long id) {
        Zone zone = zoneService.getZoneById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "suceess", zone));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateZone(@PathVariable Long id, @RequestBody Zone zone) {
        Zone updatedZone = zoneService.updateZone(id, zone);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "suceess", updatedZone));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteZone(@PathVariable Long id) {
        zoneService.deleteZone(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK, "suceess", null));
    }
}
