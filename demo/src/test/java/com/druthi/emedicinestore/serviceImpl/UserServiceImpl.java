package com.druthi.emedicinestore.serviceImpl;

import com.druthi.emedicinestore.entity.User;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import com.druthi.emedicinestore.repository.UserRepository;
import com.druthi.emedicinestore.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    User user = User.builder()
            .userId(1L)
            .userName("user")
            .phoneNumber("1234567890")
            .password("password")
            .email("email@gmai.com")
            .build();
    User user1 = User.builder()
            .userId(2L)
            .userName("user1")
            .phoneNumber("1234567890")
            .password("password")
            .email("email1@gmai.com")
            .build();

    List<User> users = List.of(user1);

    @Test
    void addUser() throws Exception {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User newUser = userService.addUser(user);
        assertEquals(user,newUser);
    }

    @Test
    void getUserById() throws Exception {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Long userId = 1L;
        User found = userService.getUserById(userId);
        assertEquals(found,user);
    }

    @Test
    void getAllUsers(){
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<User> returnedList = userService.getAllUsers();
        assertEquals(returnedList, users);
    }

    @Test
    void updateUser() throws EntityNotFoundException {
        Long userId = 1L;
        User updatedUser = new User();
        updatedUser.setUserId(userId);
        updatedUser.setUserName("User 1");
        updatedUser.setPassword("newPass");
        updatedUser.setPhoneNumber("8674567989");
        updatedUser.setEmail("email2@gmail.com");
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when((userRepository.save(user))).thenReturn(updatedUser);

        User result = userService.updateUserById(updatedUser, userId);
        assertEquals(updatedUser.getUserName(),result.getUserName());

    }

    @Test
    void deleteCustomer() throws EntityNotFoundException {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        userService.deleteUser(1L);
    }


}
