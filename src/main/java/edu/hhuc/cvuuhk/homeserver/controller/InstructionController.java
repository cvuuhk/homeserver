package edu.hhuc.cvuuhk.homeserver.controller;

import edu.hhuc.cvuuhk.homeserver.entity.Instruction;
import edu.hhuc.cvuuhk.homeserver.repository.InstructionRepository;
import edu.hhuc.cvuuhk.homeserver.service.InstructionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/ins")
public class InstructionController {

  @Resource InstructionService service;
  @Resource InstructionRepository repository;

  @PostMapping("/add")
  @ResponseBody
  public String add(@RequestBody @Validated Instruction instruction, Principal principal) {
    final String username = principal.getName();
    final String instructionName = instruction.getName();
    log.info("用户：" + username + "尝试添加指令：" + instructionName);
    repository.save(instruction);
    log.info("用户：" + username + "添加指令：" + instructionName);
    return "添加：" + instructionName + "成功";
  }

  @PostMapping("/delete/{name}")
  @ResponseBody
  public String delete(@PathVariable("name") String name, Principal principal) {
    final String username = principal.getName();
    final Instruction instruction = service.getInstructionByName(name);
    log.info("用户：" + username + "尝试删除指令：" + name);
    service.delete(instruction);
    log.info("用户：" + username + "删除指令：" + name);
    return "删除：" + name + "成功";
  }
}
