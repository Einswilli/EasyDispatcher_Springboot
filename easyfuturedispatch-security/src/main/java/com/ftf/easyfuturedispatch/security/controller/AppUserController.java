package com.ftf.easyfuturedispatch.security.controller;
import com.ftf.easyfuturedispatch.security.entities.AppUser;
import com.ftf.easyfuturedispatch.security.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("easyfuturedispatch-security/api/v1")
public class AppUserController {


    @Autowired
   // @Qualifier(value = "appUserService")
            AppUserService appUserService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * Method to fetch all events from the db.
     *
     * @return
     */
    @GetMapping("/appUsers")
    @ResponseStatus(HttpStatus.OK)
    public Collection<AppUser> getAll() {
        System.out.println("-------> : getAllEvents");
        logger.debug("Getting all AppUsers.");
        return appUserService.getAllAppUsers();
    }

    /**
     * Method to fetch event by id.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Optional<AppUser> getById(@PathVariable(value = "id") Long id) {
        logger.debug("Getting events with AppUser-id= {}.", id);
        return appUserService.findAppUserById(id);
    }

    /**
     * Method to update event by id.
     *
     * @param id
     * @param appUser
     * @return
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable(value = "id") Long id, @RequestBody AppUser appUser) {
        logger.debug("Updating event with AppUser-id= {}.", id);
        appUser.setId(id);
        appUserService.updateAppUser(appUser);
        return "event record for AppUser-id= " + id + " updated.";
    }

    /**
     * Method to delete event by id.
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable(value = "id") Long id) {
        logger.debug("Deleting event with appUser-id= {}.", id);
        appUserService.deleteAppUserById(id);
        return "event record for appUser-id= " + id + " deleted.";
    }

    /**
     * Method to delete all events from the db.
     *
     * @return
     */
    @DeleteMapping(value = "/deleteall")
    public String deleteAll() {
        logger.debug("Deleting all user.");
        appUserService.deleteAllAppUsers();
        return "All appUser records deleted.";
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser appUser) {
        return appUserService.saveUser(appUser.getAppUserName(),appUser.getAppUserPassword(), appUser.getAppUserFirstName(), appUser.getAppUserLastName());
    }

    //@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    //@PostMapping("/confirm-account")
    @GetMapping("/confirm-account")
    public AppUser confirmAppUserAccount(@RequestParam String confirmationToken) {
        System.out.println("controller confirmAppUserAccount");
        return appUserService.confirmAppUserAccount(confirmationToken);
    }




    @PostMapping("/login")
    public AppUser login(@RequestBody AppUser appUser) {
        logger.debug("loadUserByUserName.");
        System.out.println("....................."+appUser);

        System.out.println("....................."+ appUserService.findAppUserByAppUserName(appUser.getAppUserName()));

      return  appUserService.findAppUserByAppUserName(appUser.getAppUserName());
    }


}




