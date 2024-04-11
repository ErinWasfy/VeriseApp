package com.verise.veganapp.restcontroller;

import com.verise.veganapp.entities.User;
import com.verise.veganapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/postData")
    public ResponseEntity<Long> saveData(@RequestBody User user)
    {
        Long id=userService.saveData(user);
        return new ResponseEntity<Long>(id, HttpStatus.CREATED);
    }
}
