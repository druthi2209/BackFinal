package com.druthi.emedicinestore.controller;

import com.druthi.emedicinestore.entity.Medicine;
import com.druthi.emedicinestore.entity.User;
import com.druthi.emedicinestore.exception.UserNotCreatedException;
import com.druthi.emedicinestore.exception.UserNotFoundException;
import com.druthi.emedicinestore.exception.UserNotUpdatedException;
import com.druthi.emedicinestore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("getUserById/{userId}")
    public User getUserById(@PathVariable Long userId) throws Exception{
        logger.info("Getting User By Id!");
        return userService.getUserById(userId);
    }

    @GetMapping("getUserByName/{name}")
    public List<User> getUserByName(@RequestParam String name) throws Exception{
        logger.info("Finding user by name!");
        return (List<User>) userService.getUserByName(name);
    }

    @PostMapping("addUser")
    public User addUser(@RequestBody User user) throws Exception{
        logger.info("Adding new user!");
        return userService.addUser(user);
    }

    @PutMapping("updateUserById/{userId}")
    public User updateUserById(@RequestBody User user, @PathVariable Long userId) throws Exception {
        logger.info("Updating existing user!");
        return userService.updateUserById(user, userId);
    }

    @DeleteMapping("deleteUserById/{userId}")
    public String deleteUserById(@PathVariable Long userId) throws Exception{
        logger.info("Deleting existing user!");
        return userService.deleteUser(userId);
    }

}
