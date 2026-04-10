package lk.ijse.automationcontrolservice.controller;

import lk.ijse.automationcontrolservice.dto.ResponseDTO;
import lk.ijse.automationcontrolservice.dto.SensorDataDTO;
import lk.ijse.automationcontrolservice.entity.AutomationLog;
import lk.ijse.automationcontrolservice.repo.AutomationRepo;
import lk.ijse.automationcontrolservice.service.AutomationService;
import lk.ijse.automationcontrolservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automation")
@RequiredArgsConstructor
@CrossOrigin
public class AutomationController {
    private final AutomationService automationService;

    @PostMapping("/process")
    public ResponseEntity<ResponseDTO> receiveData(@RequestBody SensorDataDTO sensorDataDTO){
        try{
             automationService.processSensorData(sensorDataDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.OK, "Zone Created", null));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @GetMapping("/logs")
    public ResponseEntity<ResponseDTO> getLogs(){
        try{
            List<AutomationLog> logs = automationService.getAllLogs();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(VarList.OK, logs.toString(), null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
}
