package lk.ijse.zonemanagementservice.service;

import lk.ijse.zonemanagementservice.dto.DeviceRequestDTO;
import lk.ijse.zonemanagementservice.dto.DeviceResponseDTO;
import lk.ijse.zonemanagementservice.dto.IOTAuthRequestDTO;
import lk.ijse.zonemanagementservice.dto.IOTAuthResponseDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


public interface IotService {
    public Mono<Void> registerUser(IOTAuthRequestDTO authRequestDTO);
    public Mono<IOTAuthResponseDTO> loginUser(IOTAuthRequestDTO authRequestDTO);
    public Mono<DeviceResponseDTO> registerDevice(String token, DeviceRequestDTO deviceRequestDTO);
}
