package com.vote_Entities;

import javax.persistence.*;

@Entity
@Table(name = "votes",
       uniqueConstraints = @UniqueConstraint(columnNames = {"aadhar_id", "election_id"})) // prevent duplicate votes
public class Votes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private int voteId;

    // Many Votes → One Aadhar (one vote per election per aadhar)
    @ManyToOne
    @JoinColumn(name = "aadhar_id", nullable = false)
    private Aadhar aadhar;

    // Many Votes → One Candidate
    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    // Many Votes → One Election
    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

    // ── Constructors ──────────────────────────────
    public Votes() {}

    public Votes(Aadhar aadhar, Candidate candidate, Election election) {
        this.aadhar = aadhar;
        this.candidate = candidate;
        this.election = election;
    }

    // ── Getters & Setters ─────────────────────────
    public int getVoteId()                     { return voteId; }
    public void setVoteId(int voteId)          { this.voteId = voteId; }

    public Aadhar getAadhar()                  { return aadhar; }
    public void setAadhar(Aadhar aadhar)       { this.aadhar = aadhar; }

    public Candidate getCandidate()            { return candidate; }
    public void setCandidate(Candidate c)      { this.candidate = c; }

    public Election getElection()              { return election; }
    public void setElection(Election election) { this.election = election; }

    @Override
    public String toString() {
        return "Votes{voteId=" + voteId
                + ", aadharId=" + (aadhar != null ? aadhar.getAadharId() : "N/A")
                + ", candidateId=" + (candidate != null ? candidate.getCandidateId() : "N/A")
                + ", electionId=" + (election != null ? election.getElectionId() : "N/A") + "}";
    }
}