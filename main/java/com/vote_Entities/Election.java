package com.vote_Entities;

import javax.persistence.*;

@Entity
@Table(name = "election")
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "election_id")
    private int electionId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    // "ACTIVE" or "CLOSED"
    @Column(name = "status", nullable = false)
    private String status;

    // ── Constructors ──────────────────────────────
    public Election() {}

    public Election(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // ── Getters & Setters ─────────────────────────
    public int getElectionId()                   { return electionId; }
    public void setElectionId(int electionId)    { this.electionId = electionId; }

    public String getTitle()                     { return title; }
    public void setTitle(String title)           { this.title = title; }

    public String getDescription()               { return description; }
    public void setDescription(String desc)      { this.description = desc; }

    public String getStatus()                    { return status; }
    public void setStatus(String status)         { this.status = status; }

    @Override
    public String toString() {
        return "Election{electionId=" + electionId + ", title='" + title + "', status='" + status + "'}";
    }
}