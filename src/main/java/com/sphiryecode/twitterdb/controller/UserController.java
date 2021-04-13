package com.sphiryecode.twitterdb.controller;

import com.sphiryecode.twitterdb.entity.User;
import com.sphiryecode.twitterdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired UserService userService;

    @GetMapping("/public/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/public/users/{tag}")
    public ResponseEntity<User> getUser(@PathVariable String tag) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByTag(tag));
    }

}