package edu.hhuc.cvuuhk.homeserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeserverApplication implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(HomeserverApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {}
}
