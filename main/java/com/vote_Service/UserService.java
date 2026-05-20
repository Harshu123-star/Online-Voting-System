package com.vote_Service;

import com.vote_Entities.User;

public interface UserService {
    void registerUser(User user);
    void viewUserById(int userId);
    void viewAllUsers();
    User getUserObject(int userId);   // needed by Aadhar menu
    boolean verifyLogin(String email, String password);
}