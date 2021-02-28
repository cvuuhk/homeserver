package edu.hhuc.cvuuhk.homeserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
public class DeviceController {
  @GetMapping("/all")
  public String getAllDevice() {
    return "todo";
  }
}
