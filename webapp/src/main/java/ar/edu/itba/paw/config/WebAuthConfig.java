package ar.edu.itba.paw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.paw.config")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {
  public static final String APP_KEY = "C5LBMe8rmZCBwcdhFNBJn6Z9Da1QX6uHQZS4fEEBzSU=";  // Created using openssl

	@Autowired
	DataSource dataSource;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }


  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
      authenticationProvider.setUserDetailsService(userDetailsService);
      authenticationProvider.setPasswordEncoder(passwordEncoder());
      return authenticationProvider;
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.userDetailsService(userDetailsService)
        .authorizeRequests()
          .anyRequest().anonymous()
//      .authorizeRequests()
//        .antMatchers("/").anonymous()
//        .antMatchers("/users/register").anonymous()
//        .anyRequest().authenticated()
      .and().formLogin()
        .usernameParameter("j_email")
        .passwordParameter("j_password")
        .defaultSuccessUrl("/index", false)
        .loginPage("/")
      .and().rememberMe()
      	.userDetailsService(userDetailsService)
      	.key(APP_KEY)
      	.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
      .and().logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
      .and().exceptionHandling()
        .accessDeniedPage("/not_found")
      .and().csrf().disable();
  }

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
      JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
      tokenRepositoryImpl.setDataSource(dataSource);
      return tokenRepositoryImpl;
	}

  @Override
  public void configure(final WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/style/**", "/script/**", "/img/**", "/favicon.ico", "/403");
  }

  @Bean(name = "authenticationManager")
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
