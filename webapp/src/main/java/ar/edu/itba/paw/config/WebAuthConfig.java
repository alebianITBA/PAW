package ar.edu.itba.paw.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.paw.config")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	 
	@Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService)
        	.authorizeRequests()
                .antMatchers("/create_user").anonymous()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
            .and().formLogin()
                .usernameParameter("j_email")
                .passwordParameter("j_password")
                .defaultSuccessUrl("/index", false)
                .loginPage("/")
            .and().rememberMe()
                .userDetailsService(userDetailsService)
                .key("mysupersecretketthatnobodyknowsabout")
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
            .and().logout()
                .logoutUrl("/logout")
                .logoutUrl("/users/me/logout")
                .logoutSuccessUrl("/")
            .and().exceptionHandling()
                .accessDeniedPage("/403")
            .and().csrf().disable();
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/style/**", "/script/**", "/img/**", "/favicon.ico", "/403");
	}
    
    @Bean(name="authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
