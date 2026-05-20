package com.vote_Service;

import com.vote_Entities.Aadhar;

public interface AadharService {
    void registerAadhar(Aadhar aadhar);
    void getAadharById(int aadharId);
    void getAadharByUserId(int userId);
    void viewAllAadhars();
    Aadhar getAadharObject(int aadharId);  // needed by Voting
}