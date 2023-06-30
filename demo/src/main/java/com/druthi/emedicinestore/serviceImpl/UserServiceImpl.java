package com.druthi.emedicinestore.serviceImpl;

import com.druthi.emedicinestore.entity.User;
import com.druthi.emedicinestore.exception.EntityNotCreatedException;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import com.druthi.emedicinestore.repository.UserRepository;
import com.druthi.emedicinestore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) throws EntityNotCreatedException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        if(newUser == null){
            logger.error("New User cannot be created!");
            throw new EntityNotCreatedException("User could not be created. Try again later! ");
        }
        logger.info("Added user successfully!");
        return newUser;
    }

    @Override
    public User getUserById(Long userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId).get();
        if(user == null){
            logger.error("User with specified Id cannot be found!");
            throw new EntityNotFoundException("User with specified Id cannot be found.");
        }
        logger.info("Found user!");
        return user;
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByEmail(userName);
    }

    @Override
    public User updateUserById(User user, Long userId) throws EntityNotFoundException {
        User user1 = userRepository.findById(userId).get();
        if (user1 == null) {
            logger.error("User with specified Id cannot be found");
            throw new EntityNotFoundException("User with specified Id cannot be found");
        }
        user1 = userRepository.save(user);
        logger.info("Updated user successfully!");
        return user1;
    }

    @Override
    public String deleteUser(Long userId) throws EntityNotFoundException{
        logger.info("Deleted user successfully!");
        User user = userRepository.findById(userId).get();
        if (user == null) {
            logger.error("User with specified Id cannot be found");
            throw new EntityNotFoundException("User with specified Id cannot be found");
        }
        userRepository.delete(user);
        logger.info("Deleted user with the specified Id successfully!");
        return "Deleted user with the specified Id successfully!";
    }
}
