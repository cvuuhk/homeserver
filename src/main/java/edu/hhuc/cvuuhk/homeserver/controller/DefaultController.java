package edu.hhuc.cvuuhk.homeserver.controller;

import edu.hhuc.cvuuhk.homeserver.repository.DeviceTypeRepository;
import edu.hhuc.cvuuhk.homeserver.repository.ExecuteHistoryRepository;
import edu.hhuc.cvuuhk.homeserver.repository.UserLoginRepository;
import edu.hhuc.cvuuhk.homeserver.service.MqttPublishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.security.Principal;

@Controller
public class DefaultController {
  @Resource UserLoginRepository userLoginRepository;
  @Resource DeviceTypeRepository deviceTypeRepository;
  @Resource MqttPublishService mqttSendService;
  @Resource ExecuteHistoryRepository executeHistoryRepository;

  @GetMapping("")
  public String index(Model model, Principal principal) {
    var user = userLoginRepository.findUserLoginByUsername(principal.getName());
    model.addAttribute("user", user);
    model.addAttribute("types", deviceTypeRepository.findAll());
    model.addAttribute("logs", executeHistoryRepository.findAll());
    return "index";
  }
}
