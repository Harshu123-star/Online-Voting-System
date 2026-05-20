package com.vote_Entities;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // ── Constructors ──────────────────────────────
    public Admin() {}

    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // ── Getters & Setters ─────────────────────────
    public int getAdminId()                  { return adminId; }
    public void setAdminId(int adminId)      { this.adminId = adminId; }

    public String getName()                  { return name; }
    public void setName(String name)         { this.name = name; }

    public String getEmail()                 { return email; }
    public void setEmail(String email)       { this.email = email; }

    public String getPassword()              { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Admin{adminId=" + adminId + ", name='" + name + "', email='" + email + "'}";
    }
}