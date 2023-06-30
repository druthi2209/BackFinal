package com.druthi.emedicinestore.service;

import com.druthi.emedicinestore.entity.User;
import com.druthi.emedicinestore.exception.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUserById(Long userId) throws EntityNotFoundException;

    User addUser(User user) throws EntityNotCreatedException;

    User updateUserById(User user, Long userId) throws EntityNotFoundException;

    String deleteUser(Long userId) throws EntityNotFoundException;

    List<User> getAllUsers();

    User findUserByUserName(String userName);

}
