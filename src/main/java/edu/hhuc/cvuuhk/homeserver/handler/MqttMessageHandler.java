package edu.hhuc.cvuuhk.homeserver.handler;

import edu.hhuc.cvuuhk.homeserver.entity.DeviceHistory;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceHistoryRepository;
import edu.hhuc.cvuuhk.homeserver.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import javax.annotation.Resource;

public class MqttMessageHandler implements MessageHandler {
  @Resource DeviceRepository deviceRepository;
  @Resource DeviceHistoryRepository deviceHistoryRepository;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {
    String msg = message.getPayload().toString();
    logger.info("接收到 mqtt 消息：" + msg);
    String[] temp = msg.split(":", 2);
    if (deviceRepository.findDeviceByDeviceName(temp[0]) != null) {
      deviceHistoryRepository.save(new DeviceHistory(temp[0], temp[1]));
      logger.info("记录设备" + temp[0] + "状态：" + temp[1]);
    }
  }
}
