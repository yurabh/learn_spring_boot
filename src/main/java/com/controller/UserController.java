package com.controller;

import com.domain.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User saveAndReturnMan(@RequestBody final User user) {
        return userService.save(user);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<User> getManById(@PathVariable("id") final int userId) {
        return new ResponseEntity<>(userService.find(userId), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public void update(@RequestBody final User user) {
        userService.update(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") final int userId) {
        userService.delete(userId);
    }
}
