package edu.hhuc.cvuuhk.homeserver.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "login")
@Entity
public class UserLogin implements UserDetails {
  @Id
  @Column(name = "username", nullable = false, columnDefinition = "varchar(20)")
  private String username;

  @Column(name = "password", nullable = false, columnDefinition = "char(60)")
  private String password;

  @Column(name = "role", nullable = false, length = 20)
  private String role;

  @Column(name = "locked", nullable = false)
  private Boolean locked = false;

  @Column(name = "enabled", nullable = false)
  private Boolean enabled = true;

  @Column(name = "account_expired", nullable = false)
  private Boolean accountExpired = false;

  @Column(name = "credentials_expired", nullable = false)
  private Boolean credentialsExpired = false;

  public String getRole() {
    return role;
  }

  public UserLogin setRole(String role) {
    this.role = role;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public UserLogin setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public Boolean getAccountExpired() {
    return accountExpired;
  }

  public UserLogin setAccountExpired(Boolean accountExpired) {
    this.accountExpired = accountExpired;
    return this;
  }

  public Boolean getCredentialsExpired() {
    return credentialsExpired;
  }

  public UserLogin setCredentialsExpired(Boolean credentialsExpired) {
    this.credentialsExpired = credentialsExpired;
    return this;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> list = new ArrayList<>();
    list.add(new SimpleGrantedAuthority("ROLE_" + role));
    return list;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public UserLogin setPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public UserLogin setUsername(String username) {
    this.username = username;
    return this;
  }

  @Override
  public boolean isAccountNonExpired() {
    return !accountExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !credentialsExpired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public UserLogin setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  @Override
  public String toString() {
    return "UserLogin{"
        + "username="
        + username
        + '\''
        + "password="
        + password
        + '\''
        + "role="
        + role
        + '\''
        + "locked="
        + locked
        + '\''
        + "enabled="
        + enabled
        + '\''
        + "accountExpired="
        + accountExpired
        + '\''
        + "credentialsExpired="
        + credentialsExpired
        + '\''
        + '}';
  }
}
