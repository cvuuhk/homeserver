package edu.hhuc.cvuuhk.homeserver.repository;

import edu.hhuc.cvuuhk.homeserver.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DeviceRepository extends JpaRepository<Device, String> {
  Device findDeviceByName(String deviceName);

  Set<Device> findDevicesByType(String type);
}
