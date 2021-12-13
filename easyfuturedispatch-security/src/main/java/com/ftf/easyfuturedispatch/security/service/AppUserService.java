package com.ftf.easyfuturedispatch.security.service;

import com.ftf.easyfuturedispatch.security.entities.AppRole;
import com.ftf.easyfuturedispatch.security.entities.AppUser;

import java.util.Collection;
import java.util.Optional;


public interface AppUserService {
  public AppUser saveUser(String appUserName, String appUserPassword, String appUserFirstName, String appUserLastName);

  public AppRole saveRole(AppRole role);

  public AppUser findAppUserByAppUserName(String appUserName);

  public void addRoleToUser(String appUserName, String roleName);

  public Collection<AppUser> getAllAppUsers();

  public Optional<AppUser> findAppUserById(Long id);

  public void deleteAppUserById(Long id);

  public void updateAppUser(AppUser user);

  public void deleteAllAppUsers();

  public void saveUser(AppUser appUser);

  public AppUser confirmAppUserAccount(String confirmationToken);


}


