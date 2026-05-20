package com.vote_Entities;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private int resultId;

    // One Result → One Election
    @OneToOne
    @JoinColumn(name = "election_id", nullable = false, unique = true)
    private Election election;

    // Winner candidate
    @ManyToOne
    @JoinColumn(name = "winner_candidate_id", nullable = false)
    private Candidate winner;

    @Column(name = "winner_votes", nullable = false)
    private int winnerVotes;

    // ── Constructors ──────────────────────────────
    public Result() {}

    public Result(Election election, Candidate winner, int winnerVotes) {
        this.election = election;
        this.winner = winner;
        this.winnerVotes = winnerVotes;
    }

    // ── Getters & Setters ─────────────────────────
    public int getResultId()                       { return resultId; }
    public void setResultId(int resultId)          { this.resultId = resultId; }

    public Election getElection()                  { return election; }
    public void setElection(Election election)     { this.election = election; }

    public Candidate getWinner()                   { return winner; }
    public void setWinner(Candidate winner)        { this.winner = winner; }

    public int getWinnerVotes()                    { return winnerVotes; }
    public void setWinnerVotes(int winnerVotes)    { this.winnerVotes = winnerVotes; }

    @Override
    public String toString() {
        return "Result{resultId=" + resultId
                + ", election=" + (election != null ? election.getTitle() : "N/A")
                + ", winner=" + (winner != null ? winner.getName() : "N/A")
                + ", winnerVotes=" + winnerVotes + "}";
    }
}