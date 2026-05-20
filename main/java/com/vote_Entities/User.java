package com.vote_Entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone")
    private String phone;

    // ── Constructors ──────────────────────────────
    public User() {}

    public User(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    // ── Getters & Setters ─────────────────────────
    public int getUserId()               { return userId; }
    public void setUserId(int userId)    { this.userId = userId; }

    public String getName()              { return name; }
    public void setName(String name)     { this.name = name; }

    public String getEmail()             { return email; }
    public void setEmail(String email)   { this.email = email; }

    public String getPassword()          { return password; }
    public void setPassword(String pw)   { this.password = pw; }

    public String getPhone()             { return phone; }
    public void setPhone(String phone)   { this.phone = phone; }

    @Override
    public String toString() {
        return "User{userId=" + userId + ", name='" + name + "', email='" + email + "', phone='" + phone + "'}";
    }
}