package edu.hhuc.cvuuhk.homeserver.repository;

import edu.hhuc.cvuuhk.homeserver.entity.ExecuteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecuteHistoryRepository extends JpaRepository<ExecuteHistory, Integer> {
  void deleteAllByDeviceName(String name);
  void  deleteAllByInstructionId(Integer id);
}
