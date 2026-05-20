package com.vote_Service;

import com.vote_Entities.Election;

public interface ElectionService {
    void createElection(Election election);
    void viewElectionById(int electionId);
    void viewAllElections();
    void closeElection(int electionId);
    Election getElectionObject(int electionId);  // needed by Voting / Candidate
}