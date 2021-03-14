package web.controller;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.Model.Role;
import web.Model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.*;

@RestController
public class MyRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/getUsers/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user;
    }

    @GetMapping(value = "/getUsers")
    public ResponseEntity<List<User>> adminPage() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/getUsers")
    public String addUser(@RequestBody Map<String, Object> payload) {
        User user = new User();
        List<String> roleIds = (ArrayList)payload.get("roles");
        Set<Role> roles = new HashSet<>();
            for (String i : roleIds) {
                roles.add(roleService.findById(Long.parseLong(i)).get());
                user.setRoles(roles);
            }

        user.setFirstName((String)payload.get("firstName"));
        user.setLastName((String)payload.get("lastName"));
        user.setEmail((String)payload.get("email"));
        user.setAge((Integer)payload.get("age"));
        user.setPassword((String)payload.get("password"));

        userService.saveUser(user);
        return "200";
    }

    @DeleteMapping("/getUsers/{id}")
    public Optional<User> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return userService.findById(id);
    }

    @PutMapping("/getUsers/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody Map<String, Object> payload) {
        User user = new User();
        List<String> roleIds = (ArrayList)payload.get("roles");
        Set<Role> currentRoles = userService.findById(id).get().getRoles();
        Set<Role> roles = new HashSet<>();
        if(roleIds != null) {
            for (String i : roleIds) {
                roles.add(roleService.findById(Long.parseLong(i)).get());
                user.setRoles(roles);
            }
        } else user.setRoles(currentRoles);

        user.setId(id);
        user.setFirstName((String)payload.get("firstName"));
        user.setLastName((String)payload.get("lastName"));
        user.setEmail((String)payload.get("email"));
        user.setAge((Integer)payload.get("age"));
        user.setPassword((String)payload.get("password"));

        userService.saveUser(user);
        return String.valueOf(user.getId());
    }

}
