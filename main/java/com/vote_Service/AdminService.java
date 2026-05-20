package com.vote_Service;

import com.vote_Entities.Admin;

public interface AdminService {
    void registerAdmin(Admin admin);
    void viewAdminById(int adminId);
    void viewAllAdmins();
    boolean verifyLogin(String email, String password);
}