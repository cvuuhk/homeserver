package edu.hhuc.cvuuhk.homeserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class MosquittoService {
  public void AddUser(String username, String password) throws IOException {
    Runtime.getRuntime()
        .exec("mosquitto_passwd -b /etc/mosquitto/passwd" + " " + username + " " + password);
    log.info("向 mosquitto 中添加用户：" + username);
    reload();
  }

  public void DeleteUser(String username) throws IOException {
    Runtime.getRuntime().exec("mosquitto_passwd -D /etc/mosquitto/passwd" + " " + username);
    log.info("从 mosquitto 中移除用户：" + username);
    reload();
  }

  private void reload() throws IOException {
    Runtime.getRuntime().exec("sudo systemctl reload mosquitto.service");
    log.info("重载 mosquitto");
  }
}
