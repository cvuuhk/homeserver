package edu.hhuc.cvuuhk.homeserver.service;

import edu.hhuc.cvuuhk.homeserver.entity.Device;
import edu.hhuc.cvuuhk.homeserver.entity.Instruction;
import edu.hhuc.cvuuhk.homeserver.exception.InstructionException;
import edu.hhuc.cvuuhk.homeserver.repository.InstructionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

//todo
@Service
public class InstructionService {
  @Resource InstructionRepository repository;

  @Transactional
  public void addInstruction(Instruction instruction) {
    repository.save(instruction);
  }

  @Transactional
  public void delete(Device device) {}

  public Instruction getInstructionByName(String instructionName) throws InstructionException {
    Instruction instruction = repository.findInstructionByName(instructionName);
    if (instruction == null) throw new InstructionException("没有该指令");
    return instruction;
  }
}
