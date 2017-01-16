package ar.edu.itba.paw.config;

import ar.edu.itba.paw.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.paw.config")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {
  public static final String APP_KEY = "C5LBMe8rmZCBwcdhFNBJn6Z9Da1QX6uHQZS4fEEBzSU=";  // Created using openssl

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private UserService userService;

  public WebAuthConfig() {
    super(true);
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/").permitAll()
      .antMatchers("/favicon.ico").permitAll()
      .antMatchers("**/*.html").permitAll()
      .antMatchers("**/*.css").permitAll()
      .antMatchers("**/*.js").permitAll()
      .antMatchers("/api/v1/login").permitAll()
      // For user registration
      .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
      // For Angular requests
      .antMatchers(HttpMethod.OPTIONS).permitAll()
      .anyRequest().authenticated().and()
      .addFilterBefore(new StatelessAuthenticationFilter(userService), UsernamePasswordAuthenticationFilter.class)
      .exceptionHandling().and()
      .anonymous().and()
      .servletApi().and()
      .headers().cacheControl();
  }

  @Bean(name = "authenticationManager")
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
