package edu.hhuc.cvuuhk.homeserver.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttPublishService {
  void publish(String data);

  void publish(@Header(MqttHeaders.TOPIC) String topic, String data);
}
