package edu.hhuc.cvuuhk.homeserver.service;

import edu.hhuc.cvuuhk.homeserver.entity.Instruction;
import edu.hhuc.cvuuhk.homeserver.exception.InstructionException;
import edu.hhuc.cvuuhk.homeserver.repository.ExecuteHistoryRepository;
import edu.hhuc.cvuuhk.homeserver.repository.InstructionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Slf4j
public class InstructionService {
  @Resource InstructionRepository repository;
  @Resource ExecuteHistoryRepository executeHistoryRepository;

  @Transactional
  public void addInstruction(Instruction instruction) {
    repository.save(instruction);
  }

  @Transactional
  public void delete(Instruction instruction) {
    final Integer id = instruction.getId();
    final String name = instruction.getName();

    executeHistoryRepository.deleteAllByInstructionId(id);
    log.info("删除指令：" + name + "所有的执行记录");
    repository.delete(instruction);
    log.info("删除指令：" + name);
  }

  public Instruction getInstructionByName(String instructionName) throws InstructionException {
    Instruction instruction = repository.findInstructionByName(instructionName);
    if (instruction == null) throw new InstructionException("没有该指令");
    return instruction;
  }
}
