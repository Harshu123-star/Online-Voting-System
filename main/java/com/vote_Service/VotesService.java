package com.vote_Service;

import com.vote_Entities.Votes;

public interface VotesService {
    void castVote(int aadharId, int candidateId, int electionId);
    void viewVotesByElection(int electionId);
    void viewAllVotes();
}