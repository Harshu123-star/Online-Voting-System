package com.vote_Entities;

import javax.persistence.*;

@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private int candidateId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "party")
    private String party;

    // Many Candidates → One Election
    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

    // ── Constructors ──────────────────────────────
    public Candidate() {}

    public Candidate(String name, String party, Election election) {
        this.name = name;
        this.party = party;
        this.election = election;
    }

    // ── Getters & Setters ─────────────────────────
    public int getCandidateId()                    { return candidateId; }
    public void setCandidateId(int candidateId)    { this.candidateId = candidateId; }

    public String getName()                        { return name; }
    public void setName(String name)               { this.name = name; }

    public String getParty()                       { return party; }
    public void setParty(String party)             { this.party = party; }

    public Election getElection()                  { return election; }
    public void setElection(Election election)     { this.election = election; }

    @Override
    public String toString() {
        return "Candidate{candidateId=" + candidateId + ", name='" + name + "', party='" + party
                + "', electionId=" + (election != null ? election.getElectionId() : "N/A") + "}";
    }
}