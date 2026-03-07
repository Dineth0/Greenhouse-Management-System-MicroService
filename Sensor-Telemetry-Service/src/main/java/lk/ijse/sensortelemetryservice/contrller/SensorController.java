package lk.ijse.sensortelemetryservice.contrller;

import lk.ijse.sensortelemetryservice.dto.ResponseDTO;
import lk.ijse.sensortelemetryservice.dto.SensorDataDTO;
import lk.ijse.sensortelemetryservice.service.SensorService;
import lk.ijse.sensortelemetryservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
@CrossOrigin
public class SensorController {

    private final SensorService sensorService;

    @GetMapping("/latest")
    public ResponseEntity<ResponseDTO> getLatestReading(){
        SensorDataDTO sensorDataDTO = sensorService.getLatestFetch();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "suceess", sensorDataDTO));
    }
}
