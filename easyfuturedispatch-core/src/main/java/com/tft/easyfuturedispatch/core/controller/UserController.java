package com.tft.easyfuturedispatch.core.controller;

import com.tft.easyfuturedispatch.core.dao.UserRepository;
import com.tft.easyfuturedispatch.core.entitie.User;
import com.tft.easyfuturedispatch.core.exception.ResourceNotFoundException;
import com.tft.easyfuturedispatch.core.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/easyfuturedispatch/api/v1")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @GetMapping("/users")
    public List<User> index(){
        return userRepository.findAll();
    }
    @GetMapping("/user/{id}")
    public User find(@PathVariable("id") long id) throws ResourceNotFoundException{
        return  userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Aucun element trouver"));
    }
    @PostMapping("/user")
    public User store(@RequestBody User user){
        user.setId(sequenceGeneratorService.generateSequence(user.SEQUENCE_NAME));
        return userRepository.save(user);
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@PathVariable("id") long id, @RequestBody User user) throws ResourceNotFoundException{
        User l = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Aucun element trouver"));
        l.setUserEmail(user.getUserEmail());
        l.setUserPassword(user.getUserPassword());
        l.setUserProfile(user.getUserProfile());
        l.setUserFirstName(user.getUserFirstName());
        l.setUserLastName(user.getUserLastName());

        final User l1 = userRepository.save(l);
        return ResponseEntity.ok(l1);
    }
}
