package lk.ijse.automationcontrolservice.service.impl;

import lk.ijse.automationcontrolservice.client.ZoneClient;
import lk.ijse.automationcontrolservice.dto.SensorDataDTO;
import lk.ijse.automationcontrolservice.dto.ZoneThresholdDTO;
import lk.ijse.automationcontrolservice.entity.AutomationLog;
import lk.ijse.automationcontrolservice.repo.AutomationRepo;
import lk.ijse.automationcontrolservice.service.AutomationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutomationServiceImpl implements AutomationService {

    private final AutomationRepo automationRepo;
    private final ZoneClient zoneClient;


    @Override
    public void processSensorData(SensorDataDTO data) {
        ZoneThresholdDTO zoneThresholdDTO = zoneClient.getZone(data.getZoneId());

        String action = null;

                if(data.getTemperature() > zoneThresholdDTO.getMaxTemp()){
                    action = "TURN_FAN_OFF";
                }
                else if(data.getTemperature() < zoneThresholdDTO.getMinTemp()){
                    action = "TURN_FAN_ON";
                }
                if (action != null) {
                    AutomationLog automationLog = new AutomationLog();
                    automationLog.setAction(String.valueOf(data.getZoneId()));
                    automationLog.setTemperature(data.getTemperature());
                    automationLog.setAction(action);
                    automationLog.setTime(LocalDateTime.now().toString());

                    automationRepo.save(automationLog);
                }
    }
    public List<AutomationLog> getAllLogs() {
        return automationRepo.findAll();
    }
}
