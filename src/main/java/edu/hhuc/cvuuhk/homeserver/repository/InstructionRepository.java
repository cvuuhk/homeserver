package edu.hhuc.cvuuhk.homeserver.repository;

import edu.hhuc.cvuuhk.homeserver.entity.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface InstructionRepository extends JpaRepository<Instruction, Integer> {
  Instruction findInstructionByName(String instructionName);
  Set<Instruction> findInstructionsByType(String type);
}
