package edu.hhuc.cvuuhk.homeserver.entity;

import javax.persistence.*;
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
  @Enumerated(EnumType.STRING)
  private DeviceType type;

  @Column(name = "comment")
  private String comment;

  @Column(name = "registerDateTime", nullable = false)
  private LocalDateTime registerDateTime;

  public Device(String deviceName, String deviceNameZh, DeviceType type, String comment) {
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

  public DeviceType getType() {
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
