package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

/*    http.headers()
        .frameOptions().disable()
        .httpStrictTransportSecurity().disable()
        .xssProtection().disable();*/
    http.authorizeRequests().mvcMatchers(HttpMethod.POST, "/api/customer/create").permitAll();
    http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/api/customer").permitAll();
  }
}
