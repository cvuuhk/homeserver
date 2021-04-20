package edu.hhuc.cvuuhk.homeserver.config;

import edu.hhuc.cvuuhk.homeserver.service.UserLoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Resource UserLoginService loginService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/login","/static/**")
        .permitAll()
        .antMatchers("/device/**", "/user/**")
        .hasRole("user")
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .and()
        .csrf()
        .disable();
    http.headers().contentTypeOptions().disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
