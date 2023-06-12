package com.druthi.emedicinestore.serviceImpl;

import com.druthi.emedicinestore.entity.Medicine;
import com.druthi.emedicinestore.entity.User;
import com.druthi.emedicinestore.exception.MedicineNotFoundException;
import com.druthi.emedicinestore.exception.UserNotCreatedException;
import com.druthi.emedicinestore.exception.UserNotFoundException;
import com.druthi.emedicinestore.exception.UserNotUpdatedException;
import com.druthi.emedicinestore.repository.UserRepository;
import com.druthi.emedicinestore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) throws UserNotCreatedException {
        User newUser = userRepository.save(user);
        if(newUser == null){
            logger.error("New User cannot be created!");
            throw new UserNotCreatedException("User could not be created. Try again later! ");
        }
        logger.info("Added user successfully!");
        return newUser;
    }

    @Override
    public User getUserById(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).get();
        if(user == null){
            logger.error("User with specified Id cannot be found!");
            throw new UserNotFoundException("User with specified Id cannot be found.");
        }
        logger.info("Found user!");
        return user;
    }

    @Override
    public List<User> getUserByName(String name) throws UserNotFoundException {
        List<User> users = userRepository.findByName(name);
        if(users.size() == 0){
            logger.error("User with specified name cannot be found!");
            throw new UserNotFoundException("User with specified name cannot be found.");
        }
        logger.info("Found user!");
        return users;
    }

    @Override
    public User updateUserById(User user, Long userId) throws UserNotFoundException, UserNotUpdatedException {
        User newUser = userRepository.findById(userId).get();
        if (newUser == null) {
            logger.error("User with specified Id cannot be found");
            throw new UserNotFoundException("User with specified Id cannot be found");
        }
        if(newUser.getUserName() != user.getUserName()){
            newUser.setUserName(user.getUserName());
        }
        else if(newUser.getName() != user.getName()){
            newUser.setName(user.getName());
        }
        else if (newUser.getEmail() != user.getEmail()){
            newUser.setEmail(user.getEmail());
        }
        else if (newUser.getPassword() != user.getPassword()) {
            newUser.setPassword(user.getPassword());
        }
        else if(newUser.getPhoneNumber() != user.getPhoneNumber()) {
            newUser.setPhoneNumber(user.getPhoneNumber());
        }
        else if(newUser.getAddress() != user.getAddress()){
            newUser.setAddress(user.getAddress());
        }
        if (newUser == user) {
            logger.error("User could not be updated!");
            throw new UserNotUpdatedException("User could not be updated");
        }
        logger.info("Updated user successfully!");
        return newUser;
    }

    @Override
    public String deleteUser(Long userId) throws UserNotFoundException{
        logger.info("Deleted user successfully!");
        User user = userRepository.findById(userId).get();
        if (user == null) {
            logger.error("User with specified Id cannot be found");
            throw new UserNotFoundException("User with specified Id cannot be found");
        }
        userRepository.deleteById(userId);
        logger.info("Deleted user with the specified Id successfully!");
        return "Deleted user with the specified Id successfully!";
    }
}
