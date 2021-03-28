package edu.hhuc.cvuuhk.homeserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "device")
public class Device {

  @Id
  @Column(name = "deviceName", nullable = false)
  private String deviceName;

  @Column(name = "deviceNameZh")
  private String deviceNameZh;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "comment")
  private String comment;

  @Column(name = "registerDateTime", nullable = false)
  private LocalDateTime registerDateTime;

  public Device(String deviceName, String deviceNameZh, String type, String comment) {
    this.registerDateTime = LocalDateTime.now();
    this.deviceName = deviceName;
    this.deviceNameZh = deviceNameZh;
    this.type = type;
    this.comment = comment;
  }

  public Device() {}

  public String getDeviceName() {
    return deviceName;
  }

  public String getDeviceNameZh() {
    return deviceNameZh;
  }

  public String getType() {
    return type;
  }

  public String getComment() {
    return comment;
  }

  public LocalDateTime getRegisterDateTime() {
    return registerDateTime;
  }

  @Override
  public String toString() {
    return "Device{"
        + "deviceName="
        + deviceName
        + '\''
        + "deviceNameZh="
        + deviceNameZh
        + '\''
        + "type="
        + type
        + '\''
        + "comment="
        + comment
        + '\''
        + "registerDateTime="
        + registerDateTime
        + '\''
        + '}';
  }
}
