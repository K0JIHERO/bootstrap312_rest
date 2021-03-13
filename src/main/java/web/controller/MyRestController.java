package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.Model.User;
import web.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {

    @Autowired
    private UserService userService;


    @GetMapping("/getUsers")
    public List<User> getAllUsers() {
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping("/getUsers/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user;
    }

    @PostMapping("/getUsers")
    public User createUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }


}
