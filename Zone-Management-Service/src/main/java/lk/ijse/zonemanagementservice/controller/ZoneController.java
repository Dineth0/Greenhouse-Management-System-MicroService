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
@RequestMapping("/api/zone")
@CrossOrigin
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createZone(@RequestBody Zone zone){
        try{
            Zone createdZone = zoneService.createZone(zone);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"Zone Created",createdZone));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error,e.getMessage(),null));
        }
    }
}
