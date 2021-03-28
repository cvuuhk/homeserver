package edu.hhuc.cvuuhk.homeserver.repository;

import edu.hhuc.cvuuhk.homeserver.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {
  Device findDeviceByDeviceName(String deviceName);
}
