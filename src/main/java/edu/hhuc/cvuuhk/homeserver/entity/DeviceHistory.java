package edu.hhuc.cvuuhk.homeserver.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "device_history")
public class DeviceHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "deviceName", nullable = false)
  private String deviceName;

  @Column(name = "datetime", nullable = false)
  private LocalDateTime datetime;

  @Column(name = "status", nullable = false)
  private String status;

  public DeviceHistory(String deviceName, String status) {
    this.deviceName = deviceName;
    this.status = status;
    this.datetime = LocalDateTime.now();
  }

  public DeviceHistory() {}

  public Integer getId() {
    return id;
  }

  public DeviceHistory setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public DeviceHistory setDeviceName(String deviceName) {
    this.deviceName = deviceName;
    return this;
  }

  public LocalDateTime getDatetime() {
    return datetime;
  }

  public DeviceHistory setDatetime(LocalDateTime datetime) {
    this.datetime = datetime;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public DeviceHistory setStatus(String status) {
    this.status = status;
    return this;
  }

  @Override
  public String toString() {
    return "DeviceHistory{"
        + "id="
        + id
        + '\''
        + "deviceName="
        + deviceName
        + '\''
        + "datetime="
        + datetime
        + '\''
        + "status="
        + status
        + '\''
        + '}';
  }
}
