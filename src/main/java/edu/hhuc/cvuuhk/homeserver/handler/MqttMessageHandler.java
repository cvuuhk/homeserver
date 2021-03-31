package edu.hhuc.cvuuhk.homeserver.handler;

import edu.hhuc.cvuuhk.homeserver.entity.Device;
import edu.hhuc.cvuuhk.homeserver.entity.DeviceStatus;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceStatusRepository;
import edu.hhuc.cvuuhk.homeserver.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import javax.annotation.Resource;

@Slf4j
public class MqttMessageHandler implements MessageHandler {

  @Resource DeviceStatusRepository repository;
  @Resource DeviceService service;

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {
    String msg = message.getPayload().toString();
    log.info("接收到 mqtt 消息：" + msg);

    String[] temp = parse(msg);
    final String deviceName = temp[0];
    final String status = temp[1];

    final Device device = service.getDeviceByName(deviceName);
    repository.save(new DeviceStatus(deviceName, status));

    log.info("设备：" + deviceName + "状态：" + status);
  }

  private String[] parse(String msg) throws MessagingException {
    if (!msg.matches("\\S+:\\S+")) {
      log.info("mqtt 消息格式有误");
      throw new MessagingException("mqtt 消息格式错误");
    }

    final String[] ret = msg.split(":", 2);
    if (ret[1].length() > 128) {
      log.info("mqtt 消息过长");
      throw new MessagingException("mqtt 消息体过长");
    }

    return msg.split(":", 2);
  }
}
