package com.vote_Exception;

public class ElectionNotFoundException extends VotingAppException {
    public ElectionNotFoundException(int id) {
        super("❌ Election not found with ID: " + id);
    }
}