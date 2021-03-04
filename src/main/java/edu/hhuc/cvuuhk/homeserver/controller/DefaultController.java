package edu.hhuc.cvuuhk.homeserver.controller;

import edu.hhuc.cvuuhk.homeserver.repository.UserLoginRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.security.Principal;

@Controller
public class DefaultController {
  @Resource UserLoginRepository userLoginRepository;

  @GetMapping("")
  public String index(Model model, Principal principal) {
    var user = userLoginRepository.findUserLoginByUsername(principal.getName());
    model.addAttribute("user", user);
    return "index";
  }
}
