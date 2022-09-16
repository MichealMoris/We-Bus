package com.genius.webus.service;

import com.genius.webus.error.DuplicatedEntryException;
import com.genius.webus.error.NotFoundException;
import com.genius.webus.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService{

    public List<User> getAllUsers();
    public User getUserByID(Long userID) throws NotFoundException;
    public User addUser(User user) throws DuplicatedEntryException;
    public User addUserToBus(User user);
    public void deleteUserByID(Long userID) throws NotFoundException;
    public User updateUserByID(Long id, User user) throws NotFoundException;
    public User getUserByPhoneNumber(String userPhoneNumber)throws NotFoundException;
    //public void pojoData();
}
