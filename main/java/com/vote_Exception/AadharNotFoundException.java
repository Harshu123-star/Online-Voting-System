package com.vote_Exception;

public class AadharNotFoundException extends VotingAppException {
    public AadharNotFoundException(int id) {
        super("❌ Aadhar not found with ID: " + id);
    }
    public AadharNotFoundException(String message) {
        super(message);
    }
}