package edu.hhuc.cvuuhk.homeserver.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "device_status")
@Data
@NoArgsConstructor
public class DeviceStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "device_name", nullable = false)
  private String deviceName;

  @Column(name = "status", nullable = false)
  private String status;

  @CreationTimestamp
  @Column(name = "datetime", nullable = false)
  private Date datetime;

  public DeviceStatus(String deviceName, String status) {
    this.deviceName = deviceName;
    this.status = status;
  }
}
