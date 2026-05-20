package com.vote_Exception;

public class CandidateNotFoundException extends VotingAppException {
    public CandidateNotFoundException(int id) {
        super("❌ Candidate not found with ID: " + id);
    }
}