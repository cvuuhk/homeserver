package edu.hhuc.cvuuhk.homeserver.service;

import edu.hhuc.cvuuhk.homeserver.entity.Device;
import edu.hhuc.cvuuhk.homeserver.entity.DeviceType;
import edu.hhuc.cvuuhk.homeserver.entity.Instruction;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceRepository;
import edu.hhuc.cvuuhk.homeserver.repository.InstructionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Set;

@Service
@Slf4j
public class DeviceTypeService {
  @Resource DeviceService deviceService;
  @Resource InstructionService instructionService;
  @Resource DeviceRepository deviceRepository;
  @Resource InstructionRepository instructionRepository;

  @Transactional
  public void delete(DeviceType type) {
    final String typename = type.getName();

    log.info("删除类型：" + typename + "下的所有设备：");
    Set<Device> devices = deviceRepository.findDevicesByType(typename);
    for (Device device : devices) {
      deviceService.deleteDevice(device);
    }
    log.info("删除该类型：" + typename + "下的所有指令：");
    Set<Instruction> instructions = instructionRepository.findInstructionsByType(typename);
    for (Instruction instruction : instructions) {
      instructionService.delete(instruction);
    }
  }
}
