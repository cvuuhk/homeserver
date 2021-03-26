package edu.hhuc.cvuuhk.homeserver.entity;

import java.time.LocalDateTime;

public interface Device {
  // 设备id
  Integer getDeviceId();

  // 获取设备名称
  String getDeviceName();

  // 获取设备类型
  DeviceType getDeviceType();

  // 获取设备状态，获取的信息与设备类型有关
  String getDeviceStatus();

  // 获取设备添加的时间
  LocalDateTime getDateTime();

  // 开启设备
  boolean on();

  // 关闭设备
  boolean off();

  // 执行一些自定义指令
  boolean action(String action);
}
