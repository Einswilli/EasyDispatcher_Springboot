package com.ftf.easyfuturedispatch.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private BCryptPasswordEncoder  bCryptPasswordEncoder;
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    System.out.println("dans configure....");
    String baseUrl="/easyfuturedispatch-security/api/v1";
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    //http.formLogin();
    http.authorizeRequests().antMatchers("/login/**",baseUrl+"/register/**", baseUrl+"/confirm-account/**",baseUrl+"/appUsers/**").permitAll();
    http.authorizeRequests().antMatchers(baseUrl+"/appUsers/**",baseUrl+"/appRoles/**").hasAnyAuthority("ADMIN");
    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(new JWTAuthentificationFilter(authenticationManager()));
    http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

  }


}
