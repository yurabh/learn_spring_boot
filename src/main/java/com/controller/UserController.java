package com.controller;

import com.domain.User;
import com.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Api(value = "UserController")
public class UserController {

    @ApiModelProperty(notes = "User Service",
            name = "userService",
            required = true, value = "service")
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Save User in the System",
            response = User.class,
            tags = "saveUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "not found")})
    @PostMapping("/save")
    public User saveAndReturnMan(@RequestBody final User user) {
        return userService.save(user);
    }

    @ApiOperation(value = "Get User from the System",
            response = User.class,
            tags = "getUserById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "not found")})
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<User> getManById(@PathVariable("id") final int userId) {
        return new ResponseEntity<>(userService.find(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "Update User from the System",
            tags = "updateUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "not found")})
    @PutMapping(value = "/update")
    public void update(@RequestBody final User user) {
        userService.update(user);
    }

    @ApiOperation(value = "Delete User from the System",
            tags = "deleteUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "not found")})
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") final int userId) {
        userService.delete(userId);
    }
}
