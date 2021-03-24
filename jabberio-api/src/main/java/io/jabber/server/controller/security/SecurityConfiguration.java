package io.jabber.server.controller.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Contains the security-related configuration for the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.sessionManagement().disable()
        .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/profile").and()
        .authorizeRequests()
        .antMatchers(
            "/users",
            "/status"
        ).permitAll()
        .anyRequest().authenticated();
  }

  /**
   * Gets the encoder that is used to encode raw passwords.
   * @return The password encoder.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Gets the password salt generator.
   * @return The password salt generator.
   */
  @Bean
  public StringKeyGenerator saltGenerator() {
    return KeyGenerators.string();
  }

}
