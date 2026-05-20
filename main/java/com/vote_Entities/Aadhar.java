package com.vote_Entities;

import javax.persistence.*;

@Entity
@Table(name = "aadhar")
public class Aadhar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aadhar_id")
    private int aadharId;

    // One User → One Aadhar
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "dob", nullable = false)
    private String dob;           // stored as "yyyy-MM-dd"

    @Column(name = "address", nullable = false)
    private String address;

    // ── Constructors ──────────────────────────────
    public Aadhar() {}

    public Aadhar(User user, String dob, String address) {
        this.user = user;
        this.dob = dob;
        this.address = address;
    }

    // ── Getters & Setters ─────────────────────────
    public int getAadharId()                 { return aadharId; }
    public void setAadharId(int aadharId)    { this.aadharId = aadharId; }

    public User getUser()                    { return user; }
    public void setUser(User user)           { this.user = user; }

    public String getDob()                   { return dob; }
    public void setDob(String dob)           { this.dob = dob; }

    public String getAddress()               { return address; }
    public void setAddress(String address)   { this.address = address; }

    @Override
    public String toString() {
        return "Aadhar{aadharId=" + aadharId + ", userId=" + (user != null ? user.getUserId() : "N/A")
                + ", dob='" + dob + "', address='" + address + "'}";
    }
}