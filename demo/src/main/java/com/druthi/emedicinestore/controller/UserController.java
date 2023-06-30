package com.druthi.emedicinestore.controller;

import com.druthi.emedicinestore.configuration.CustomUserDetails;
import com.druthi.emedicinestore.configuration.JWTHelper;
import com.druthi.emedicinestore.configuration.JWTRequest;
import com.druthi.emedicinestore.configuration.JWTResponse;
import com.druthi.emedicinestore.entity.User;
import com.druthi.emedicinestore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTHelper jwtHelper;

    //    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
    @PostMapping("login")
    public ResponseEntity<Object> loginUser(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid JWTRequest jwtRequest) throws UsernameNotFoundException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtHelper.generateToken(userDetails);


        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        JWTResponse jwtResponse = JWTResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername())
                .role(roles.get(0)).build();
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }


    @GetMapping("getUserById/{userId}")
//    @PreAuthorize("hasRole('USER')")
    public User getUserById(@PathVariable Long userId) throws Exception{
        logger.info("Getting User By Id!");
        return userService.getUserById(userId);
    }

    @GetMapping("getAllUsers")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("addUser")
    public User addUser(@RequestBody User user) throws Exception{
        logger.info("Adding new user!");
        return userService.addUser(user);
    }

    @GetMapping("findByUserName")
    public User findUserByUserName(@RequestParam("userName") String userName){
        return userService.findUserByUserName(userName);
    }

    @PutMapping("updateUserById/{userId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public User updateUserById(@RequestBody User user, @PathVariable Long userId) throws Exception {
        logger.info("Updating existing user!");
        return userService.updateUserById(user, userId);
    }

    @DeleteMapping("deleteUserById/{userId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUserById(@PathVariable Long userId) throws Exception{
        logger.info("Deleting existing user!");
        return userService.deleteUser(userId);
    }

}
