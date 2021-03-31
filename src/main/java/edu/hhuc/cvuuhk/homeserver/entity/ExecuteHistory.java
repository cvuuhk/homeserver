package edu.hhuc.cvuuhk.homeserver.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "execute_history")
public class ExecuteHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "device_name", nullable = false)
  private String deviceName;

  @Column(name = "instruction_id", nullable = false)
  private Integer instructionId;

  @Column(name = "arg")
  private String arg;

  @Column(name = "execute_by", nullable = false)
  private String executeBy;

  @CreationTimestamp
  @Column(name = "datetime", nullable = false)
  private Date datetime;

  public ExecuteHistory(String deviceName, Integer instructionId, String arg, String executeBy) {
    this.deviceName = deviceName;
    this.instructionId = instructionId;
    this.arg = arg;
    this.executeBy = executeBy;
  }
}
