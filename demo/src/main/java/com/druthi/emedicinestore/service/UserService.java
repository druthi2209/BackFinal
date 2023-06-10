package com.druthi.emedicinestore.service;

import com.druthi.emedicinestore.entity.User;
import com.druthi.emedicinestore.exception.UserNotCreatedException;
import com.druthi.emedicinestore.exception.UserNotFoundException;
import com.druthi.emedicinestore.exception.UserNotUpdatedException;

public interface UserService {
    User getUserById(Long userId) throws UserNotFoundException;

    User addUser(User user) throws UserNotCreatedException;

    User updateUserById(User user, Long userId) throws UserNotFoundException, UserNotUpdatedException;

    String deleteUser(Long userId) throws UserNotFoundException;
}
