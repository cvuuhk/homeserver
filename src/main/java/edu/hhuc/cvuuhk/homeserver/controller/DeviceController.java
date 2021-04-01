package edu.hhuc.cvuuhk.homeserver.controller;

import edu.hhuc.cvuuhk.homeserver.entity.Device;
import edu.hhuc.cvuuhk.homeserver.entity.Instruction;
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

  @RequestMapping("/all")
  public String getAllDevice(Model model) {
    model.addAttribute("devices", service.getAll());
    return "device";
  }

  @PostMapping("/add")
  @ResponseBody
  public String addDevice(@RequestBody @Validated Device device, Principal principal)
      throws IOException {
    final String username = principal.getName();
    log.info("用户：" + username + "尝试添加设备：" + device.getName());

    service.addDevice(device);
    final String password = UUID.randomUUID().toString();
    final String deviceName = device.getName();
    mosquittoService.AddUser(deviceName, password);
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
    mosquittoService.DeleteUser(deviceName);

    return "设备：" + deviceName + "删除成功";
  }

  @PostMapping("/execute")
  @ResponseBody
  public String execute(@RequestBody @Validated ExecuteBody body, Principal principal) {
    final String username = principal.getName();
    final String deviceName = body.getDeviceName();
    final String instructionName = body.getInstructionName();
    final String arg = body.getArg();
    final Device device = service.getDeviceByName(deviceName);
    final Instruction instruction = instructionService.getInstructionByName(instructionName);
    log.info("用户：" + username + "尝试操作设备：" + deviceName + "执行：" + instructionName + " " + arg);

    service.execute(username, device, instruction, arg);

    log.info("用户：" + username + "操作设备 " + deviceName + " 执行：" + instructionName + " " + arg);
    return "执行成功";
  }

  @GetMapping("/{deviceName}")
  @ResponseBody
  public Device getDeviceByDeviceName(@PathVariable("deviceName") String deviceName) {
    return service.getDeviceByName(deviceName);
  }
}
