package kg.PerfectJob.BookStore.controller;

import kg.PerfectJob.BookStore.dto.UserPasswordDTO;
import kg.PerfectJob.BookStore.entity.User;
import kg.PerfectJob.BookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @PutMapping("/password")
    public User changePassword(@RequestBody UserPasswordDTO userPasswordDTO) {
        return userService.changePassword(userPasswordDTO);
    }

    @GetMapping("/profile")
    public User getProfileInfo(Principal principal) {
        return userService.findUserByEmail(principal.getName());
    }
}
