package me.study.spring_boot_get_start.user;

import org.springframework.web.bind.annotation.*;

/**
 * Created by dongchul on 2019-11-18.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return user;
    }
}
