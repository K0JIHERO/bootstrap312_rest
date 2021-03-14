//package web.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import web.Model.User;
//import web.service.UserService;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//public class reserveRESTC {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/getUsers/{id}")
//    public Optional<User> getUserById(@PathVariable Long id) {
//        Optional<User> user = userService.findById(id);
//        return user;
//    }
//
//    @GetMapping("/getUsers")
//    public List<User> getAllUsers() {
//        List<User> users = userService.findAll();
//        return users;
//    }
//
//    @PostMapping("/getUsers")
//    public User user(@RequestBody User user) {
//        userService.saveUser(user);
//        System.out.println("NOT SAVED" + user);
//        return user;
//    }
//
//    @DeleteMapping("/getUsers/{id}")
//    public Optional<User> deleteUser(@PathVariable Long id) {
//        userService.deleteById(id);
//        return userService.findById(id);
//    }
//
//    @PutMapping("/getUsers/{id}")
//    public String updateUser(@PathVariable(value = "id") Long id) {
//        Optional<User> ouser = userService.findById(id);
//        User user = ouser.get();
//        userService.saveUser(user);
//        System.out.println("NOT ! ! ! updated " + user + user.getId());
//        return String.valueOf(user.getId());
//    }
//}
