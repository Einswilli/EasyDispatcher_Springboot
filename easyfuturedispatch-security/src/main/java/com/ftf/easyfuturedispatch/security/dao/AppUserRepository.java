package com.ftf.easyfuturedispatch.security.dao;
import com.ftf.easyfuturedispatch.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
  public AppUser findByAppUserName(String appUserName);
  //public AppUser findByAppUserEmail(String appUserEmail);
}
