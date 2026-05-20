package com.vote;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.ServiceImpl.VotesServiceImpl;

public class VotesServiceTest {

    VotesServiceImpl votesService = new VotesServiceImpl();

    @Test
    void castVote_Test() {
        // aadharId=1, candidateId=1, electionId=1
        // Should not throw any exception
        Assertions.assertDoesNotThrow(() -> votesService.castVote(1, 1, 1));
    }

    @Test
    void castVote_DuplicateVote_Test() {
        // Same aadhar voting again in same election — should print error, not crash
        Assertions.assertDoesNotThrow(() -> votesService.castVote(1, 1, 1));
    }

    @Test
    void castVote_InvalidAadhar_Test() {
        // Aadhar ID 9999 doesn't exist — should handle gracefully
        Assertions.assertDoesNotThrow(() -> votesService.castVote(9999, 1, 1));
    }

    @Test
    void viewVotesByElection_Test() {
        Assertions.assertDoesNotThrow(() -> votesService.viewVotesByElection(1));
    }
}