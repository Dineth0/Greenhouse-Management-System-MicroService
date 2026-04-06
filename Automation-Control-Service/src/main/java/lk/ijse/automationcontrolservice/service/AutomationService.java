package lk.ijse.automationcontrolservice.service;

import lk.ijse.automationcontrolservice.dto.SensorDataDTO;
import lk.ijse.automationcontrolservice.entity.AutomationLog;

import java.util.List;

public interface AutomationService {
    public void processSensorData(SensorDataDTO data);
    public List<AutomationLog> getAllLogs();

    }
