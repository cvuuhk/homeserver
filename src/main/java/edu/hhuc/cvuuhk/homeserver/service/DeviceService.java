package edu.hhuc.cvuuhk.homeserver.service;

import edu.hhuc.cvuuhk.homeserver.entity.Device;
import edu.hhuc.cvuuhk.homeserver.entity.ExecuteHistory;
import edu.hhuc.cvuuhk.homeserver.entity.Instruction;
import edu.hhuc.cvuuhk.homeserver.exception.DeviceException;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceRepository;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceStatusRepository;
import edu.hhuc.cvuuhk.homeserver.repository.ExecuteHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class DeviceService {
  @Resource DeviceRepository repository;
  @Resource ExecuteHistoryRepository executeHistoryRepository;
  @Resource DeviceStatusRepository deviceStatusRepository;

  @Resource MqttPublishService mqttPublishService;

  @Transactional
  public void addDevice(Device device) {
    log.info("添加设备：" + device.getName());
    repository.save(device);
  }

  @Transactional
  public void deleteDevice(Device device) {
    final String deviceName = device.getName();
    executeHistoryRepository.deleteAllByDeviceName(deviceName);
    log.info("删除设备：" + deviceName + "的指令执行记录");
    deviceStatusRepository.deleteAllByDeviceName(deviceName);
    log.info("删除设备：" + deviceName + "的历史数据");
    repository.delete(device);
    log.info("删除设备：" + deviceName);
  }

  public Device getDeviceByName(String deviceName) throws DeviceException {
    final Device device = repository.findDeviceByName(deviceName);
    if (device == null) throw new DeviceException("没有该设备");
    return device;
  }

  public List<Device> getAll() {
    return repository.findAll();
  }

  public void execute(String username, Device device, Instruction instruction, String arg) {
    if (!device.getType().equals(instruction.getType())) {
      throw new DeviceException("该设备无法执行其他类型设备的指令");
    }
    final String deviceName = device.getName();
    final Integer instructionId = instruction.getId();
    final String instructionName = instruction.getName();

    final String msg = deviceName + ":" + instructionName + " " + arg;
    mqttPublishService.publish(msg);
    log.info("mqtt 发送：" + msg);

    executeHistoryRepository.save(new ExecuteHistory(deviceName, instructionId, arg, username));
    log.info("用户：" + username + "操作设备：" + deviceName + "执行：" + instructionName + " " + arg);
  }

  private void check(Device device) throws DeviceException {}
}
