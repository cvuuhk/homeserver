package edu.hhuc.cvuuhk.homeserver.repository;

import edu.hhuc.cvuuhk.homeserver.entity.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DeviceStatusRepository extends JpaRepository<DeviceStatus, Integer> {
  void deleteAllByDeviceName(String name);

  Set<DeviceStatus> findAllByDeviceName(String deviceName);
}
