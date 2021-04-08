package edu.hhuc.cvuuhk.homeserver.controller;

import edu.hhuc.cvuuhk.homeserver.entity.DeviceType;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceRepository;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceTypeRepository;
import edu.hhuc.cvuuhk.homeserver.repository.InstructionRepository;
import edu.hhuc.cvuuhk.homeserver.repository.UserLoginRepository;
import edu.hhuc.cvuuhk.homeserver.service.DeviceTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

@Controller
@RequestMapping(value = "/type")
@Slf4j
public class DeviceTypeController {

  @Resource DeviceTypeRepository repository;
  @Resource DeviceTypeService service;

  @Resource DeviceRepository deviceRepository;
  @Resource InstructionRepository instructionRepository;
  @Resource UserLoginRepository userLoginRepository;

  @GetMapping(value = "/{typename}")
  public String index(@PathVariable("typename") String typename, Principal principal, Model model) {
    model.addAttribute("types", repository.findAll());
    model.addAttribute("current_type", repository.findDeviceTypeByName(typename));
    model.addAttribute("user", userLoginRepository.findUserLoginByUsername(principal.getName()));
    model.addAttribute("devices", deviceRepository.findDevicesByType(typename));
    model.addAttribute("all_ins", instructionRepository.findInstructionsByType(typename));

    return "type";
  }

  @PostMapping("/add")
  @ResponseBody
  public String add(@RequestBody @Validated DeviceType type, Principal principal) {
    final String username = principal.getName();
    final String typename = type.getName();

    log.info("用户：" + username + "尝试新建设备类型：" + typename);
    service.add(type);
    log.info("用户：" + username + "新建设备类型：" + typename);

    return "添加" + typename + "成功";
  }

  @PostMapping("/delete/{typename}")
  @ResponseBody
  public String delete(@PathVariable("typename") String typename, Principal principal) {
    final String username = principal.getName();
    final DeviceType type = service.getDeviceTypeByName(typename);
    log.info("用户：" + username + "尝试删除设备类型：" + typename);

    service.delete(type);

    log.info("用户：" + username + "删除设备类型：" + typename);
    return "删除" + typename + "成功";
  }
}
