package lk.ijse.zonemanagementservice.service.impl;

import jakarta.ws.rs.core.HttpHeaders;
import lk.ijse.zonemanagementservice.dto.DeviceRequestDTO;
import lk.ijse.zonemanagementservice.dto.DeviceResponseDTO;
import lk.ijse.zonemanagementservice.dto.IOTAuthRequestDTO;
import lk.ijse.zonemanagementservice.dto.IOTAuthResponseDTO;
import lk.ijse.zonemanagementservice.service.IotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class IotServiceImpl implements IotService {

    private final WebClient webClient;

    @Override
    public Mono<Void> registerUser(IOTAuthRequestDTO authRequestDTO) {
        return webClient.post()
                .uri("/auth/register")
                .bodyValue(authRequestDTO)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<IOTAuthResponseDTO> loginUser(IOTAuthRequestDTO authRequestDTO) {
        return webClient.post()
                .uri("/auth/login")
                .bodyValue(authRequestDTO)
                .retrieve()
                .bodyToMono(IOTAuthResponseDTO.class);
    }

    @Override
    public Mono<DeviceResponseDTO> registerDevice(String token, DeviceRequestDTO deviceRequestDTO) {
        return webClient.post()
                .uri("/register")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .bodyValue(deviceRequestDTO)
                .retrieve()
                .bodyToMono(DeviceResponseDTO.class);
    }
}
