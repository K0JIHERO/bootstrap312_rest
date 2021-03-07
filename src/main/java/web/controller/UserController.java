package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.Model.Role;
import web.Model.User;
import web.service.RoleService;
import web.service.UserService;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null || authentication instanceof AnonymousAuthenticationToken) {
            return "/login";
        }
        return "/login";
    }

    @GetMapping(value = "/admin")
    @Secured("ADMIN")
    public ModelAndView findAllAdmin(Principal principal, User createdUser) {
        User currentUser = userService.findByLastName2(principal.getName());
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.setViewName("/admin");
        modelAndView.addObject("users", users);
        modelAndView.addObject("currentUser", currentUser);
        modelAndView.addObject("createdUser", createdUser);
        modelAndView.addObject("allRoles", roleService.findAllRoles());
        modelAndView.addObject("updateRoles", roleService.findAllRoles());
        return modelAndView;
    }

    @GetMapping(value = "/user")
    @Secured("USER")
    public ModelAndView findAllUser(Principal currentUser) {
        User user = userService.findByLastName2(currentUser.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user");
        modelAndView.addObject("currentUser", user);
        return modelAndView;
    }

    @PostMapping("/user-create")
    @Secured("ADMIN")
    public ModelAndView createUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @PostMapping("/user-delete/{id}")
    @Secured("ADMIN")
    public ModelAndView deleteUser(Long id,User user, @RequestParam(value = "roles", required = false) Set<Long> roleIds) {
        ModelAndView modelAndView = new ModelAndView();
        Set<Role> currentRoles = userService.findById(id).get().getRoles();
        Set<Role> roles = new HashSet<>();
        if(roleIds != null) {
            for (Long i: roleIds) {
                roles.add(roleService.findById(i).get());
                user.setRoles(roles);
            }
        } else {
            user.setRoles(currentRoles);
        }
        userService.deleteById(id);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @PostMapping("/user-update/{id}")
    @Secured("ADMIN")
    public ModelAndView updateUser(Long id,User user, @RequestParam(value = "roles", required = false) Set<Long> roleIds) {
        ModelAndView modelAndView = new ModelAndView();
        Set<Role> currentRoles = userService.findById(id).get().getRoles();
        Set<Role> roles = new HashSet<>();
        if(roleIds != null) {
            for (Long i: roleIds) {
                roles.add(roleService.findById(i).get());
                user.setRoles(roles);
            }
        } else {
            user.setRoles(currentRoles);
        }
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }
}