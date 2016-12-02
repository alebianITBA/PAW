package ar.edu.itba.paw.config;

import ar.edu.itba.paw.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication implements Authentication {
  private User user;
  private boolean authenticated;

  public UserAuthentication(User user) {
    this.user = user;
    this.authenticated = true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public Object getCredentials() {
    return user.getPassword();
  }

  @Override
  public User getDetails() {
    return user;
  }

  @Override
  public Object getPrincipal() {
    return user;
  }

  @Override
  public boolean isAuthenticated() {
    return authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.authenticated = isAuthenticated;
  }

  @Override
  public String getName() {
    return user.getEmail();
  }
}
