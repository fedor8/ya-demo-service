package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication()
        .withUser("USER").password("{noop}USER").roles("USER")
        .and()
        .withUser("ADMIN").password("{noop}ADMIN").roles("ADMIN")
        .and()
        .withUser("USER2").password("{noop}USER2").roles("USER");

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests()
        .mvcMatchers(HttpMethod.POST, "/api/customer/create").permitAll()
        .mvcMatchers(HttpMethod.GET, "/api/customer").permitAll()
        .mvcMatchers(HttpMethod.DELETE, "/api/customer").access("isAuthenticated() and authentication.name == 'USER' and hasRole('USER')")
        .mvcMatchers(HttpMethod.POST, "/api/customer/*/update-salary").access("isAuthenticated() and authentication.name == 'USER' and hasRole('USER')");
    http.formLogin()
        .loginProcessingUrl("/login")
        .usernameParameter("login")
        .passwordParameter("password").permitAll();
    http.httpBasic();
  }
}
