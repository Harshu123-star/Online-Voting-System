package com.vote_Exception;

public class UserNotFoundException extends VotingAppException {
    public UserNotFoundException(int userId) {
        super("❌ User not found with ID: " + userId);
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}