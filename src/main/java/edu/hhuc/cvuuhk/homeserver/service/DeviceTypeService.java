package edu.hhuc.cvuuhk.homeserver.service;

import edu.hhuc.cvuuhk.homeserver.entity.Device;
import edu.hhuc.cvuuhk.homeserver.entity.DeviceType;
import edu.hhuc.cvuuhk.homeserver.entity.Instruction;
import edu.hhuc.cvuuhk.homeserver.exception.DeviceException;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceRepository;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceTypeRepository;
import edu.hhuc.cvuuhk.homeserver.repository.InstructionRepository;
import edu.hhuc.cvuuhk.homeserver.repository.UserLoginRepository;
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
  @Resource DeviceTypeRepository repository;
  @Resource UserLoginRepository userLoginRepository;

  public DeviceType getDeviceTypeByName(String name) {
    DeviceType type = repository.findDeviceTypeByName(name);
    if (type == null) throw new DeviceException("没有设备类型：" + name);
    return type;
  }

  @Transactional
  public void add(DeviceType type) {
    if (userLoginRepository.findUserLoginByUsername(type.getCreateBy()) == null)
      throw new DeviceException("设备创建人错误，没有用户：" + type.getCreateBy());
    if (repository.findDeviceTypeByName(type.getName()) != null)
      throw new DeviceException("该设备类型已经存在");
    repository.save(type);
    log.info("添加设备类型：" + type.getName());
  }

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
    repository.delete(type);
    log.info("删除类型：" + typename);
  }
}
