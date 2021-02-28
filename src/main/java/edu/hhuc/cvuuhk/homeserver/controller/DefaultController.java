package edu.hhuc.cvuuhk.homeserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
  @GetMapping("/")
  public String index(){
    return "index.html";
  }
}
