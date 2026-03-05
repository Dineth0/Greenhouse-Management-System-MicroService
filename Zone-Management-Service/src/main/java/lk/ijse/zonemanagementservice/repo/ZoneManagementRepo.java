package lk.ijse.zonemanagementservice.repo;

import lk.ijse.zonemanagementservice.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneManagementRepo extends JpaRepository<Zone, Long> {
}
