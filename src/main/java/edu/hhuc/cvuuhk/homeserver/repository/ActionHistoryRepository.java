package edu.hhuc.cvuuhk.homeserver.repository;

import edu.hhuc.cvuuhk.homeserver.entity.ActionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionHistoryRepository extends JpaRepository<ActionHistory, Integer> {}
