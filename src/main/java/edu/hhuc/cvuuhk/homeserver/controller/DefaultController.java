package edu.hhuc.cvuuhk.homeserver.controller;

import edu.hhuc.cvuuhk.homeserver.repository.UserLoginRepository;
import edu.hhuc.cvuuhk.homeserver.service.MqttSenderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;

@Controller
public class DefaultController {
  @Resource UserLoginRepository userLoginRepository;
  @Resource MqttSenderService mqttSenderService;

  @GetMapping("")
  public String index(Model model, Principal principal) {
    var user = userLoginRepository.findUserLoginByUsername(principal.getName());
    model.addAttribute("user", user);
    return "index";
  }

  // 发送自定义消息内容（使用默认主题）
  @GetMapping("/mqtt_pub/{data}")
  @ResponseBody
  public String test1(@PathVariable("data") String data) {
    mqttSenderService.sendToMqtt(data);
    return "发送 mqtt 消息成功，消息内容为：" + data;
  }
}
