package edu.hhuc.cvuuhk.homeserver.repository;

import edu.hhuc.cvuuhk.homeserver.entity.DeviceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceHistoryRepository extends JpaRepository<DeviceHistory, String>{

}