package lk.ijse.zonemanagementservice.service;

import lk.ijse.zonemanagementservice.entity.Zone;

public interface ZoneService {
    Zone createZone(Zone zone);
    Zone getZoneById(Long id);
    Zone updateZone(Long id,Zone zone);
}
