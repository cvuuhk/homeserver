package edu.hhuc.cvuuhk.homeserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "device_history")
public class DeviceHistory  {

  @Id
  @Column(name = "deviceName", nullable = false)
  private String deviceName;

  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "datetime", nullable = false)
  private Date datetime;

  public DeviceHistory setDeviceName(String deviceName) {
    this.deviceName = deviceName;
    return this;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public DeviceHistory setStatus(String status) {
    this.status = status;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public DeviceHistory setDatetime(Date datetime) {
    this.datetime = datetime;
    return this;
  }

  public Date getDatetime() {
    return datetime;
  }

  @Override
  public String toString() {
    return "DeviceHistory{" +
            "deviceName=" + deviceName + '\'' +
            "status=" + status + '\'' +
            "datetime=" + datetime + '\'' +
            '}';
  }
}
