package lk.ijse.automationcontrolservice.repo;


import lk.ijse.automationcontrolservice.entity.AutomationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomationRepo extends JpaRepository<AutomationLog, Long> {
}
