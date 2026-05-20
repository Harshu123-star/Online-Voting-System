package com.vote_Exception;

import com.vote_Exception.VotingAppException;

public class DuplicateVoteException extends VotingAppException {
    public DuplicateVoteException() {
        super("❌ You have already voted in this election!");
    }
}