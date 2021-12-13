//package com.tft.easyfuturedispatch.core.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class securityConfig extends WebSecurityConfigurerAdapter {
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//  http.csrf().disable();
//  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//  //http.authorizeRequests().antMatchers(HttpMethod.GET,"/client/**").permitAll();
//    http.authorizeRequests().antMatchers(HttpMethod.POST,"/client/**").permitAll();
//  //http.authorizeRequests().antMatchers(HttpMethod.GET,"/events/**").permitAll();
//  //http.authorizeRequests().antMatchers("/events/**").hasAnyAuthority("USER");
//  //http.authorizeRequests().antMatchers("/events/**").hasAnyAuthority("USER");
//    http.authorizeRequests().antMatchers("/").permitAll();
//  http.authorizeRequests().anyRequest().authenticated();
//  http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//  }
//
// /*
//
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//   // BCryptPasswordEncoder bCryptPasswordEncoder=getBCryptPasswordEncoder();
//   // auth.inMemoryAuthentication().withUser("admin").password(bCryptPasswordEncoder.encode("1234")).roles("ADMIN","USER");
//   // auth.inMemoryAuthentication().withUser("user1").password(bCryptPasswordEncoder.encode("1234")).roles("USER");
//
//  }
//
//  @Bean
//  public BCryptPasswordEncoder getBCryptPasswordEncoder(){
//    return  new BCryptPasswordEncoder();
//  }
//  */
//
//}
