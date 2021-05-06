package edu.hhuc.cvuuhk.homeserver.controller;

import edu.hhuc.cvuuhk.homeserver.entity.Device;
import edu.hhuc.cvuuhk.homeserver.entity.Instruction;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceStatusRepository;
import edu.hhuc.cvuuhk.homeserver.request_body.ExecuteBody;
import edu.hhuc.cvuuhk.homeserver.service.DeviceService;
import edu.hhuc.cvuuhk.homeserver.service.InstructionService;
import edu.hhuc.cvuuhk.homeserver.service.MosquittoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/device")
public class DeviceController {
  @Resource DeviceService service;
  @Resource InstructionService instructionService;
  @Resource MosquittoService mosquittoService;
  @Resource DeviceStatusRepository deviceStatusRepository;

  @GetMapping("/{device_name}")
  public String getAllDevice(@PathVariable("device_name") String deviceName, Model model) {
    model.addAttribute("current_device", service.getDeviceByName(deviceName));
    model.addAttribute("all_status", deviceStatusRepository.findAllByDeviceName(deviceName));
    return "device";
  }

  @PostMapping("/add")
  @ResponseBody
  public String addDevice(@RequestBody @Validated Device device, Principal principal)
      throws IOException {
    final String username = principal.getName();
    final String password = UUID.randomUUID().toString();
    final String deviceName = device.getName();

    device.setPassword(password);
    log.info("用户：" + username + "尝试添加设备：" + device.getName());

    service.addDevice(device);
//    mosquittoService.AddUser(deviceName, password);
    log.info("用户：" + username + "添加设备：" + device.getName() + "密钥：" + password);
    return "设备：" + deviceName + "添加成功，" + "密钥：" + password;
  }

  @PostMapping("/delete/{devicename}")
  @ResponseBody
  public String deleteDevice(@PathVariable("devicename") String deviceName, Principal principal)
      throws IOException {
    final String username = principal.getName();
    final Device device = service.getDeviceByName(deviceName);
    log.info("用户：" + username + "尝试删除设备：" + deviceName);

    service.deleteDevice(device);
//    mosquittoService.DeleteUser(deviceName);

    return "设备：" + deviceName + "删除成功";
  }

  @PostMapping("/execute")
  @ResponseBody
  public String execute(@RequestBody @Validated ExecuteBody body, Principal principal) {
    final String username = principal.getName();
    final String deviceName = body.getDeviceName();
    final Integer instructionId = body.getInstructionId();
    final String arg = body.getArg();
    final Device device = service.getDeviceByName(deviceName);
    final Instruction instruction = instructionService.getInstructionById(instructionId);

    log.info("用户：" + username + "尝试操作设备：" + deviceName + "执行：" + instructionId + "号指令，参数：" + arg);

    service.execute(username, device, instruction, arg);

    log.info("用户：" + username + "操作设备 " + deviceName + " 执行：" + instructionId + "号指令，参数：" + arg);
    return "执行成功";
  }
}
