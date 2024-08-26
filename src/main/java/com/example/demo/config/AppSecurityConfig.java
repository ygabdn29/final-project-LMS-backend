package com.example.demo.config;

import javax.management.RuntimeErrorException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
      http  
            .csrf().disable()
            .authorizeRequests((auth) -> {
                try {
                    auth
                            .antMatchers("/").permitAll()
                            .antMatchers("/account/**").permitAll()
                            .antMatchers("/api/account/**").permitAll()
                            .anyRequest().permitAll();
                } catch (Exception e) {
                    throw new RuntimeErrorException(null);
                }
           
            });
            return http.build();
    }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  
}
