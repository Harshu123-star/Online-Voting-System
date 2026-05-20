package com.vote_Service;

import com.vote_Entities.Candidate;

public interface CandidateService {
    void addCandidate(Candidate candidate);
    void viewCandidateById(int candidateId);
    void viewCandidatesByElection(int electionId);
    void viewAllCandidates();
    Candidate getCandidateObject(int candidateId);  // needed by Voting
}