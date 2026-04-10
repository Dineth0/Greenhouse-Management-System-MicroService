package lk.ijse.automationcontrolservice.client;

import lk.ijse.automationcontrolservice.dto.ResponseDTO;
import lk.ijse.automationcontrolservice.dto.ZoneThresholdDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zone-management-service")
public interface ZoneClient {

    @GetMapping("/api/zones/{id}")
    ResponseDTO getZone(@PathVariable Long id);
}
