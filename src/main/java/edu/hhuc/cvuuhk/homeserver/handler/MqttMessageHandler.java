package edu.hhuc.cvuuhk.homeserver.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public class MqttMessageHandler implements MessageHandler {
  @Override
  public void handleMessage(Message<?> message) throws MessagingException {
    String msg = message.getPayload().toString();
    System.out.println(
        "\n--------------------START-------------------\n"
            + "接收到订阅消息:\n"
            + msg
            + "\n---------------------END--------------------");
  }
}
