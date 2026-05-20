package com.vote_Exception;

public class AdminNotFoundException extends VotingAppException {
    public AdminNotFoundException(int id) {
        super("❌ Admin not found with ID: " + id);
    }
    public AdminNotFoundException(String message) {
        super(message);
    }
}