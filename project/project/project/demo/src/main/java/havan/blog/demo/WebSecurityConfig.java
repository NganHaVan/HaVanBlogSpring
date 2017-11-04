package havan.blog.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import havan.blog.demo.services.UserDetailServiceImp;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//	.authorizeRequests().antMatchers("/css/**","/image/**","/js/**").permitAll()
	
	@Autowired
	private UserDetailServiceImp userDetailServices;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests().antMatchers("/static/**","/css/**","/image/**","/js/**").permitAll()
		.and()
		.authorizeRequests().antMatchers("/","/users/register","/saveuser","/posts/view/{id}/").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/users/login")
			.defaultSuccessUrl("/")
			.permitAll()
		.and()
		.logout()
			.permitAll();
	}
	
	@Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth
	      .inMemoryAuthentication()
	        .withUser("user").password("password").roles("USER")
	        .and()
	        .withUser("admin").password("password").roles("ADMIN");
	    auth.userDetailsService(userDetailServices).passwordEncoder(new BCryptPasswordEncoder());
	  }
	
}
