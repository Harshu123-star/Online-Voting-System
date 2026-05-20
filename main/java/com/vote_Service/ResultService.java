package com.vote_Service;

public interface ResultService {
    void computeAndDeclareResult(int electionId);
    void viewResult(int electionId);
    void viewAllResults();
}