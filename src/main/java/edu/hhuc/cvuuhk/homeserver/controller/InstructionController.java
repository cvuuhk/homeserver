package edu.hhuc.cvuuhk.homeserver.controller;

import edu.hhuc.cvuuhk.homeserver.entity.Instruction;
import edu.hhuc.cvuuhk.homeserver.repository.InstructionRepository;
import edu.hhuc.cvuuhk.homeserver.service.InstructionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

  @GetMapping("/{id}")
  public String index(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("ins", repository.findInstructionById(id));
    return "ins";
  }

  @PostMapping("/add")
  @ResponseBody
  public String add(@RequestBody @Validated Instruction instruction, Principal principal) {
    final String username = principal.getName();
    final String instructionName = instruction.getName();
    log.info("用户：" + username + "尝试添加指令：" + instructionName);
    repository.saveAndFlush(instruction);
    log.info("用户：" + username + "添加指令：" + instructionName + "，id:" + instruction.getId());
    return "添加：" + instructionName + "成功";
  }

  @PostMapping("/delete/{id}")
  @ResponseBody
  public String delete(@PathVariable("id") Integer id, Principal principal) {
    final String username = principal.getName();
    final Instruction instruction = service.getInstructionById(id);
    final String instructionName = instruction.getName();

    log.info("用户：" + username + "尝试删除指令：" + instructionName + "，id:" + id);
    service.delete(instruction);
    log.info("用户：" + username + "删除指令：" + instructionName + "，id:" + id);
    return "删除成功";
  }
}
