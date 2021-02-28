package edu.hhuc.cvuuhk.homeserver.service;

import edu.hhuc.cvuuhk.homeserver.repository.UserLoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLoginService implements UserDetailsService {
  @Resource UserLoginRepository repository;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails details = repository.findUserLoginByUsername(username);
    if (details == null) {
      throw new UsernameNotFoundException("账户" + username + "不存在");
    }

    logger.info("用户" + username + "登陆成功");
    return details;
  }
}
