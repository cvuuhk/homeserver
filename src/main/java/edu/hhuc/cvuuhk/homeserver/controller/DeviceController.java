package edu.hhuc.cvuuhk.homeserver.controller;

import edu.hhuc.cvuuhk.homeserver.entity.Device;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/device")
public class DeviceController {

  @Resource DeviceRepository deviceRepository;

  @RequestMapping("/all")
  public String getAllDevice(Model model) {
    model.addAttribute("devices", deviceRepository.findAll());
    return "device";
  }

  @PostMapping("/add/{deviceName}")
  @ResponseBody
  public String addDevice(
      @PathVariable("deviceName") String deviceName, @RequestBody String other) {
    // todo：参数合法性检测
    if (deviceName == null || !deviceName.matches("\\w{2,32}")) return "请输入正确的 DeviceName！";
    if (deviceRepository.findDeviceByDeviceName(deviceName) != null) return "与其他设备名称重复！";

    // 解析生成相应 Device
    String[] args = other.split(",");
    deviceRepository.save(new Device(deviceName, args[0], args[1], args[2]));

    UUID password = UUID.randomUUID();
    try {
      Runtime.getRuntime()
          .exec("mosquitto_passwd -b /etc/mosquitto/passwd" + " " + deviceName + " " + password);
      Runtime.getRuntime().exec("sudo systemctl restart mosquitto.service");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "设备" + deviceName + "添加成功！\n" + "该设备密钥为：" + password;
  }

  @DeleteMapping("/delete/{deviceName}")
  @ResponseBody
  public String deleteDevice(@PathVariable("deviceName") String deviceName) {
    Device device = deviceRepository.findDeviceByDeviceName(deviceName);
    if (device == null) return "没有该设备";
    deviceRepository.delete(device);

    try {
      Runtime.getRuntime().exec("mosquitto_passwd -D /etc/mosquitto/passwd" + " " + deviceName);
      Runtime.getRuntime().exec("sudo systemctl restart mosquitto.service");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "删除成功";
  }

  @GetMapping("/{deviceName}")
  @ResponseBody
  public Device getDeviceByDeviceName(@PathVariable("deviceName") String deviceName) {
    return deviceRepository.findDeviceByDeviceName(deviceName);
  }
}
