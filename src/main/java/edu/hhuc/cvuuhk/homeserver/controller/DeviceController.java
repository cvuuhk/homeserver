package edu.hhuc.cvuuhk.homeserver.controller;

import edu.hhuc.cvuuhk.homeserver.entity.ActionHistory;
import edu.hhuc.cvuuhk.homeserver.entity.Device;
import edu.hhuc.cvuuhk.homeserver.repository.ActionHistoryRepository;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceRepository;
import edu.hhuc.cvuuhk.homeserver.service.MqttPublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/device")
public class DeviceController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Resource DeviceRepository deviceRepository;
  @Resource MqttPublishService mqttPublishService;
  @Resource ActionHistoryRepository actionHistoryRepository;

  @RequestMapping("/all")
  public String getAllDevice(Model model) {
    model.addAttribute("devices", deviceRepository.findAll());
    return "device";
  }

  @PostMapping("/add")
  @ResponseBody
  public String addDevice(@RequestBody Device device, Principal principal) {

    deviceRepository.save(device);

    UUID password = UUID.randomUUID();
    mosquittoAddUser(device.getDeviceName(), password.toString());
    actionHistoryRepository.save(
        new ActionHistory(principal.getName(), device.getDeviceName(), "add"));

    return "设备" + device.getDeviceName() + "添加成功，" + "密钥为：" + password;
  }

  @DeleteMapping("/delete/{deviceName}")
  @ResponseBody
  public String deleteDevice(@PathVariable("deviceName") String deviceName) {
    Device device = deviceRepository.findDeviceByDeviceName(deviceName);
    if (device == null) return "没有该设备";
    actionHistoryRepository.deleteActionHistoriesByDeviceName(deviceName);
    deviceRepository.delete(device);
    mosquittoDeleteUser(deviceName);

    return "删除成功";
  }

  @PostMapping("/action/{deviceName}")
  @ResponseBody
  public String action(
      @PathVariable("deviceName") String deviceName,
      @RequestBody String action,
      Principal principal) {
    if (deviceRepository.findDeviceByDeviceName(deviceName) != null) {
      mqttPublishService.publish("deviceAction", deviceName + ":" + action);
      actionHistoryRepository.save(new ActionHistory(principal.getName(), deviceName, action));
    }

    return "设备" + deviceName + "执行" + action + "指令";
  }

  @GetMapping("/{deviceName}")
  @ResponseBody
  public Device getDeviceByDeviceName(
      @PathVariable("deviceName") String deviceName, Principal principal) {
    System.out.println(principal.getName());
    return deviceRepository.findDeviceByDeviceName(deviceName);
  }

  private void mosquittoAddUser(String username, String password) {
    try {
      Runtime.getRuntime()
          .exec("mosquitto_passwd -b /etc/mosquitto/passwd" + " " + username + " " + password);
      Runtime.getRuntime().exec("sudo systemctl reload mosquitto.service");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void mosquittoDeleteUser(String username) {
    try {
      Runtime.getRuntime().exec("mosquitto_passwd -D /etc/mosquitto/passwd" + " " + username);
      Runtime.getRuntime().exec("sudo systemctl reload mosquitto.service");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
