package edu.hhuc.cvuuhk.homeserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/device")
public class DeviceController {
  @RequestMapping("/all")
  public String getAllDevice(Model model) {
    String[] devices = {"123", "456"};
    model.addAttribute("devices", devices);
    return "device";
  }
}
