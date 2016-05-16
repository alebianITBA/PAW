package ar.edu.itba.paw.config;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
    final User user = userService.findByEmail(email);
    if (user != null) {
      final Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
      authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
      return new org.springframework.security.core.userdetails.User(user.getEmail(),
          user.getPassword(), true, true, true, true, authorities);
    }

    throw new UsernameNotFoundException("No user found by the email " + email);
  }

}