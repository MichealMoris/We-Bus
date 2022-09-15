package com.genius.webus.service.implemention;

import com.genius.webus.entity.User;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.repository.UserRepository;
import com.genius.webus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByID(Long userID) throws NotFoundException{

        Optional<User> user = userRepository.findById(userID);

        if (!user.isPresent()){

            throw new NotFoundException("User with ID " + userID + " not found!");

        }

        return user.get();
    }

    @Override
    public User addUser(User user) {
        user.setPassword(getEncodedPassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUserByID(Long userID) throws NotFoundException{

        Optional<User> user = userRepository.findById(userID);

        if (!user.isPresent()){

            throw new NotFoundException("User with ID " + userID + " not found!");

        }
        userRepository.deleteById(userID);

    }

    @Override
    public User updateUserByID(Long id, User user) throws NotFoundException{

        Optional<User> userDB = userRepository.findById(id);

        if (!userDB.isPresent()){

            throw new NotFoundException("User with ID " + id + " not found!");

        }
        if (!user.getUserFullName().isEmpty() && user.getUserFullName() != null){

            userDB.get().setUserFullName(user.getUserFullName());

        }
        if (!user.getUsername().isEmpty() && user.getUsername() != null){

            userDB.get().setUsername(user.getUsername());

        }
        if (!user.getEmail().isEmpty() && user.getEmail() != null){

            userDB.get().setEmail(user.getEmail());

        }
        if (!user.getPhoneNumber().isEmpty() && user.getPhoneNumber() != null){

            userDB.get().setPhoneNumber(user.getPhoneNumber());

        }
        if (!user.getType().isEmpty() && user.getType() != null){

            userDB.get().setType(user.getType());

        }
        if (!user.getPassword().isEmpty() && user.getPassword() != null){

            userDB.get().setPassword(getEncodedPassword(user.getPassword()));

        }
        userDB.get().setAdmin(user.isAdmin());

        return userRepository.save(userDB.get());
    }

    @Override
    public User getUserByPhoneNumber(String userPhoneNumber) throws NotFoundException {

        Optional<User> user = userRepository.findUserByPhoneNumber(userPhoneNumber);

        if (!user.isPresent()){

            throw new NotFoundException("User with Phone Number " + userPhoneNumber + " not found!");

        }

        return user.get();
    }

    /*@Override
    public void pojoData() {

        User user = new User();
        user.setUserID(1L);
        user.setUserFullName("Micheal Moris");
        user.setUsername("MichealMoris1");
        user.setEmail("mesho.moris@gmail.com");
        user.setPhoneNumber("01551554053");
        user.setType("employee");
        user.setPassword("mesho123moris");
        user.setAdmin(true);
        userRepository.save(user);

    }*/

    public String getEncodedPassword(String password){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String result = encoder.encode(password);
        return result;

    }

}
