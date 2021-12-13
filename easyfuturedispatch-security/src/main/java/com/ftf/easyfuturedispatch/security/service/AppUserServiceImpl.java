package com.ftf.easyfuturedispatch.security.service;

import com.ftf.easyfuturedispatch.security.dao.AppRoleRepository;
import com.ftf.easyfuturedispatch.security.dao.AppUserRepository;
import com.ftf.easyfuturedispatch.security.dao.ConfirmationTokenRepository;
import com.ftf.easyfuturedispatch.security.entities.AppRole;
import com.ftf.easyfuturedispatch.security.entities.AppUser;
import com.ftf.easyfuturedispatch.security.entities.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService{

  @Autowired
  private AppUserRepository appUserRepository;
  @Autowired
  private AppRoleRepository appRoleRepository;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder ;
  @Autowired
  private ConfirmationTokenRepository confirmationTokenRepository;
  @Autowired
  private EmailSenderService emailSenderService;



//  public AppUserServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
//    this.appUserRepository = appUserRepository;
//    this.appRoleRepository = appRoleRepository;
//    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//  }

  @Override
  public AppUser saveUser(String appUserName, String appUserPassword, String appUserFirstName, String appUserLastName) {

    AppUser appUserControl = appUserRepository.findByAppUserName(appUserName);
    if (appUserControl != null) throw new RuntimeException("User already exist");
    //if (!password.equals(confirmedPassword))  throw new RuntimeException("Password non confirmed");
    AppUser appUser=new AppUser();
    appUser.setAppUserName(appUserName);
   // appUser.setAppUserEmail(appUserEmail);
    appUser.setAppUserFirstName(appUserFirstName);
    appUser.setAppUserLastName(appUserLastName);
    appUser.setAppUserPassword(bCryptPasswordEncoder.encode(appUserPassword));
    appUser.setAppUserIsActivated(false);

    System.out.println(appUser);
    appUserRepository.save(appUser);
    addRoleToUser(appUserName,"USER");

    //

//
//    ConfirmationToken confirmationToken = new ConfirmationToken(appUser);
//
//    confirmationTokenRepository.save(confirmationToken);

//    SimpleMailMessage mailMessage = new SimpleMailMessage();
//    mailMessage.setTo(appUser.getAppUserName());
//    mailMessage.setSubject("Complete Registration!");
//    mailMessage.setFrom("chand312902@gmail.com");
//    mailMessage.setText("To confirm your account, please click here : "
//            +"http://localhost:8081/nextevent-security/api/v1/confirm-account?confirmationToken="+confirmationToken.getConfirmationToken());
//
//    emailSenderService.sendEmail(mailMessage);

    //modelAndView.addObject("emailId", appUser.getAppUserEmail());

    //modelAndView.setViewName("successfulRegisteration");

    return appUser;
  }

  public AppUser confirmAppUserAccount(@RequestParam String confirmationToken)
  {
    ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

    if(token != null)
    {
      AppUser appUser = appUserRepository.findByAppUserName(token.getAppUser().getAppUserName());
      appUser.setAppUserIsActivated(true);
      appUserRepository.save(appUser);
      //modelAndView.setViewName("accountVerified");
      System.out.println("controller impl");
    }
    else
    {
      //modelAndView.addObject("message","The link is invalid or broken!");
      //modelAndView.setViewName("error");
    }

    return null;
  }
  // getters and setters




  @Override
  public AppRole saveRole(AppRole role) {
   return appRoleRepository.save(role);
  }

  @Override
  public AppUser findAppUserByAppUserName(String appUserName) {
    return appUserRepository.findByAppUserName(appUserName);
  }


  @Override
  public void addRoleToUser(String appUserName, String roleName) {
    AppUser appUser=appUserRepository.findByAppUserName(appUserName);
    AppRole appRole=appRoleRepository.findByRoleName(roleName);
    appUser.getRoles().add(appRole);

  }

  @Override
  public Collection<AppUser> getAllAppUsers() {
    return appUserRepository.findAll();
  }

  @Override
  public Optional<AppUser> findAppUserById(Long id) {
    return appUserRepository.findById(id);
  }

  @Override
  public void deleteAppUserById(Long id) {
    appUserRepository.deleteById(id);
  }

  @Override
  public void updateAppUser(AppUser user) {
   appUserRepository.save(user);
  }

  @Override
  public void deleteAllAppUsers() {
    appUserRepository.deleteAll();
  }

  @Override
  public void saveUser(AppUser appUser) {
    appUserRepository.save(appUser);
  }


}
