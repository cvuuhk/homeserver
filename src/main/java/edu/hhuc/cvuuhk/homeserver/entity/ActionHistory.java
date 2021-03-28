package edu.hhuc.cvuuhk.homeserver.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "action_history")
public class ActionHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "deviceName", nullable = false)
  private String deviceName;

  @Column(name = "action", nullable = false)
  private String action;

  @Column(name = "datetime", nullable = false)
  private LocalDateTime datetime;

  @Column(name = "username", nullable = false)
  private String username;

  public ActionHistory() {}

  public ActionHistory(String username, String deviceName, String action) {
    this.deviceName = deviceName;
    this.action = action;
    this.username = username;
    this.datetime = LocalDateTime.now();
  }

  public Integer getId() {
    return id;
  }

  public ActionHistory setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public ActionHistory setDeviceName(String deviceName) {
    this.deviceName = deviceName;
    return this;
  }

  public String getAction() {
    return action;
  }

  public ActionHistory setAction(String action) {
    this.action = action;
    return this;
  }

  public LocalDateTime getDatetime() {
    return datetime;
  }

  public ActionHistory setDatetime(LocalDateTime datetime) {
    this.datetime = datetime;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public ActionHistory setUsername(String username) {
    this.username = username;
    return this;
  }

  @Override
  public String toString() {
    return "ActionHistory{"
        + "id="
        + id
        + '\''
        + "deviceName="
        + deviceName
        + '\''
        + "action="
        + action
        + '\''
        + "datetime="
        + datetime
        + '\''
        + "username="
        + username
        + '\''
        + '}';
  }
}
