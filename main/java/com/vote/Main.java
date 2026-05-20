package com.vote;

import java.util.Scanner;

import com.ServiceImpl.*;
import com.vote_Entities.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static userServiceImpl     userService      = new userServiceImpl();
    static AdminServiceImpl    adminService     = new AdminServiceImpl();
    static AadharServiceImpl   aadharService    = new AadharServiceImpl();
    static ElectionServiceImpl electionService  = new ElectionServiceImpl();
    static CandidateServiceImpl candidateService = new CandidateServiceImpl();
    static VotesServiceImpl    votesService     = new VotesServiceImpl();
    static ResultServiceImpl   resultService    = new ResultServiceImpl();

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   ONLINE VOTING SYSTEM - JAVA");
        System.out.println("=====================================");

        boolean running = true;
        while (running) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. User Management");
            System.out.println("2. Admin Management");
            System.out.println("3. Aadhar Management");
            System.out.println("4. Election Management");
            System.out.println("5. Candidate Management");
            System.out.println("6. Voting");
            System.out.println("7. Results");
            System.out.println("0. Exit");
            System.out.print("Enter Choice: ");

            switch (readInt()) {
                case 1: userMenu();      break;
                case 2: adminMenu();     break;
                case 3: aadharMenu();    break;
                case 4: electionMenu();  break;
                case 5: candidateMenu(); break;
                case 6: votingMenu();    break;
                case 7: resultMenu();    break;
                case 0:
                    HibernateUtil.shutdown();
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid Choice!");
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // USER MENU
    // ─────────────────────────────────────────────────────────────
    static void userMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------ USER MENU ------");
            System.out.println("1. Register User");
            System.out.println("2. View User by ID");
            System.out.println("3. View All Users");
            System.out.println("4. Verify Login");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (readInt()) {
                case 1:
                    System.out.print("Name: ");    String name  = sc.nextLine();
                    System.out.print("Email: ");   String email = sc.nextLine();
                    System.out.print("Password: "); String pass = sc.nextLine();
                    System.out.print("Phone: ");   String phone = sc.nextLine();
                    userService.registerUser(new User(name, email, pass, phone));
                    pause(); break;
                case 2:
                    System.out.print("User ID: ");
                    userService.viewUserById(readInt());
                    pause(); break;
                case 3:
                    userService.viewAllUsers();
                    pause(); break;
                case 4:
                    System.out.print("Email: ");    String le = sc.nextLine();
                    System.out.print("Password: "); String lp = sc.nextLine();
                    System.out.println(userService.verifyLogin(le, lp) ? "✅ Login successful!" : "❌ Invalid credentials!");
                    pause(); break;
                case 0: back = true; break;
                default: System.out.println("❌ Invalid Choice!");
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // ADMIN MENU
    // ─────────────────────────────────────────────────────────────
    static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------ ADMIN MENU ------");
            System.out.println("1. Register Admin");
            System.out.println("2. View Admin by ID");
            System.out.println("3. View All Admins");
            System.out.println("4. Verify Login");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (readInt()) {
                case 1:
                    System.out.print("Name: ");    String n = sc.nextLine();
                    System.out.print("Email: ");   String e = sc.nextLine();
                    System.out.print("Password: "); String p = sc.nextLine();
                    adminService.registerAdmin(new Admin(n, e, p));
                    pause(); break;
                case 2:
                    System.out.print("Admin ID: ");
                    adminService.viewAdminById(readInt());
                    pause(); break;
                case 3:
                    adminService.viewAllAdmins();
                    pause(); break;
                case 4:
                    System.out.print("Email: ");    String le = sc.nextLine();
                    System.out.print("Password: "); String lp = sc.nextLine();
                    System.out.println(adminService.verifyLogin(le, lp) ? "✅ Login successful!" : "❌ Invalid credentials!");
                    pause(); break;
                case 0: back = true; break;
                default: System.out.println("❌ Invalid Choice!");
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // AADHAR MENU
    // ─────────────────────────────────────────────────────────────
    static void aadharMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------ AADHAR MENU ------");
            System.out.println("1. Register Aadhar");
            System.out.println("2. View Aadhar by ID");
          //  System.out.println("3. View Aadhar by User ID");
            System.out.println("3. View All Aadhars");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (readInt()) {
                case 1:
                    System.out.print("User ID: ");  int uid = readInt();
                    User user = userService.getUserObject(uid);
                    if (user == null) { System.out.println("❌ User not found!"); break; }
                    System.out.print("DOB (yyyy-MM-dd): "); String dob     = sc.nextLine();
                    System.out.print("Address: ");          String address = sc.nextLine();
                    aadharService.registerAadhar(new Aadhar(user, dob, address));
                    pause(); break;
                case 2:
                    System.out.print("Aadhar ID: ");
                    aadharService.getAadharById(readInt());
                    pause(); break;
                case 3:
                    System.out.print("User ID: ");
                    aadharService.getAadharByUserId(readInt());
                    pause(); break;
                case 4:
                    aadharService.viewAllAadhars();
                    pause(); break;
                case 0: back = true; break;
                default: System.out.println("❌ Invalid Choice!");
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // ELECTION MENU
    // ─────────────────────────────────────────────────────────────
    static void electionMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------ ELECTION MENU ------");
            System.out.println("1. Create Election");
            System.out.println("2. View Election by ID");
            System.out.println("3. View All Elections");
            System.out.println("4. Close Election");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (readInt()) {
                case 1:
                    System.out.print("Title: ");       String t = sc.nextLine();
                    System.out.print("Description: "); String d = sc.nextLine();
                    electionService.createElection(new Election(t, d, "ACTIVE"));
                    pause(); break;
                case 2:
                    System.out.print("Election ID: ");
                    electionService.viewElectionById(readInt());
                    pause(); break;
                case 3:
                    electionService.viewAllElections();
                    pause(); break;
                case 4:
                    System.out.print("Election ID to close: ");
                    electionService.closeElection(readInt());
                    pause(); break;
                case 0: back = true; break;
                default: System.out.println("❌ Invalid Choice!");
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // CANDIDATE MENU
    // ─────────────────────────────────────────────────────────────
    static void candidateMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------ CANDIDATE MENU ------");
            System.out.println("1. Add Candidate");
            System.out.println("2. View Candidate by ID");
            System.out.println("3. View Candidates by Election");
            System.out.println("4. View All Candidates");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (readInt()) {
                case 1:
                    System.out.print("Candidate Name: "); String cn = sc.nextLine();
                    System.out.print("Party: ");           String cp = sc.nextLine();
                    System.out.print("Election ID: ");     int eid   = readInt();
                    Election election = electionService.getElectionObject(eid);
                    if (election == null) { System.out.println("❌ Election not found!"); break; }
                    candidateService.addCandidate(new Candidate(cn, cp, election));
                    pause(); break;
                case 2:
                    System.out.print("Candidate ID: ");
                    candidateService.viewCandidateById(readInt());
                    pause(); break;
                case 3:
                    System.out.print("Election ID: ");
                    candidateService.viewCandidatesByElection(readInt());
                    pause(); break;
                case 4:
                    candidateService.viewAllCandidates();
                    pause(); break;
                case 0: back = true; break;
                default: System.out.println("❌ Invalid Choice!");
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // VOTING MENU
    // ─────────────────────────────────────────────────────────────
    static void votingMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------ VOTING MENU ------");
            System.out.println("1. Cast Vote");
            System.out.println("2. View Votes by Election");
            System.out.println("3. View All Votes");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (readInt()) {
                case 1:
                    System.out.print("Aadhar ID: ");    int aid = readInt();
                    System.out.print("Election ID: ");  int eid = readInt();
                    System.out.print("Candidate ID: "); int cid = readInt();
                    votesService.castVote(aid, cid, eid);
                    pause(); break;
                case 2:
                    System.out.print("Election ID: ");
                    votesService.viewVotesByElection(readInt());
                    pause(); break;
                case 3:
                    votesService.viewAllVotes();
                    pause(); break;
                case 0: back = true; break;
                default: System.out.println("❌ Invalid Choice!");
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // RESULT MENU
    // ─────────────────────────────────────────────────────────────
    static void resultMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------ RESULT MENU ------");
            System.out.println("1. Compute & Declare Result");
            System.out.println("2. View Result by Election");
            System.out.println("3. View All Results");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (readInt()) {
                case 1:
                    System.out.print("Election ID: ");
                    resultService.computeAndDeclareResult(readInt());
                    pause(); break;
                case 2:
                    System.out.print("Election ID: ");
                    resultService.viewResult(readInt());
                    pause(); break;
                case 3:
                    resultService.viewAllResults();
                    pause(); break;
                case 0: back = true; break;
                default: System.out.println("❌ Invalid Choice!");
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // UTILITIES
    // ─────────────────────────────────────────────────────────────
    static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.print("❌ Enter a valid number: ");
            }
        }
    }

    static void pause() {
        System.out.println("\nPress Enter to continue...");
        sc.nextLine();
    }
}