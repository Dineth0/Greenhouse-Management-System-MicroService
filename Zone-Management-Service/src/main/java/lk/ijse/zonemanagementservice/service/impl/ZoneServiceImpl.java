package lk.ijse.zonemanagementservice.service.impl;

import lk.ijse.zonemanagementservice.dto.DeviceRequestDTO;
import lk.ijse.zonemanagementservice.dto.DeviceResponseDTO;
import lk.ijse.zonemanagementservice.dto.IOTAuthRequestDTO;
import lk.ijse.zonemanagementservice.dto.IOTAuthResponseDTO;
import lk.ijse.zonemanagementservice.entity.Zone;
import lk.ijse.zonemanagementservice.repo.ZoneManagementRepo;
import lk.ijse.zonemanagementservice.service.IotService;
import lk.ijse.zonemanagementservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final IotService iotService;
    private final ZoneManagementRepo zoneManagementRepo;

    @Override
    public Zone createZone(Zone zone) {

        if(zone.getMinTemp() > zone.getMaxTemp()){
            throw new RuntimeException("MinTemp must be less than MaxTemp");
        }

        IOTAuthRequestDTO authRequestDTO = new IOTAuthRequestDTO("user", "12345");
//        iotService.registerUser(authRequestDTO).block();

        IOTAuthResponseDTO authResponseDTO = iotService.loginUser(authRequestDTO).block();
        String token = authResponseDTO.getAccessToken();

        DeviceRequestDTO deviceRequestDTO = new DeviceRequestDTO(zone.getName(), "Zone-A");

        DeviceResponseDTO deviceResponseDTO = iotService.registerDevice(token, deviceRequestDTO).block();

        zone.setDeviceId(deviceResponseDTO.getDeviceId());

        return zoneManagementRepo.save(zone);
    }

    @Override
    public Zone getZoneById(Long id) {
        return zoneManagementRepo.findById(id).orElseThrow(() -> new RuntimeException("No zone found"));
    }

    @Override
    public Zone updateZone(Long id, Zone zone) {
         Zone existingZone = zoneManagementRepo.findById(id).orElseThrow(() -> new RuntimeException("No zone found"));

         if(zone.getMinTemp() > zone.getMinTemp()){
             throw new RuntimeException("MinTemp must be less than MaxTemp");
         }
         existingZone.setMinTemp(zone.getMinTemp());
         existingZone.setMaxTemp(zone.getMaxTemp());

         return zoneManagementRepo.save(existingZone);
    }

    @Override
    public void deleteZone(Long id) {
        Zone zone = zoneManagementRepo.findById(id).orElseThrow(() -> new RuntimeException("No zone found"));

        zoneManagementRepo.delete(zone);
    }
}
