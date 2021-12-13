package com.ftf.easyfuturedispatch.security.security;

import com.ftf.easyfuturedispatch.security.entities.AppUser;
import com.ftf.easyfuturedispatch.security.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
  @Autowired
  private AppUserService appUserService;

  @Override
  public UserDetails loadUserByUsername(String appUserName) throws UsernameNotFoundException {
    System.out.println("......................loadUserByUsername1...................................");
    AppUser appUser= appUserService.findAppUserByAppUserName(appUserName);
    if(appUser==null)  throw new RuntimeException("User "+appUserName+"is not valid");
    Collection<GrantedAuthority> authorities=new ArrayList<>();
    appUser.getRoles().forEach(r->{
      authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
    });
    System.out.println(".......................loadUserByUsername2..................................");
    System.out.println(new User(appUser.getAppUserName(),appUser.getAppUserPassword(),authorities));
    return new User(appUser.getAppUserName(),appUser.getAppUserPassword(),authorities);
  }
}
