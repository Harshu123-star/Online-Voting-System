package com.ServiceImpl;

import com.vote.HibernateUtil;
import com.vote_Entities.*;
import com.vote_Exception.*;
import com.vote_Service.VotesService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VotesServiceImpl implements VotesService {

    @Override
    public void castVote(int aadharId, int candidateId, int electionId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Validate Aadhar
            Aadhar aadhar = session.get(Aadhar.class, aadharId);
            if (aadhar == null) throw new AadharNotFoundException(aadharId);

            // Validate Candidate
            Candidate candidate = session.get(Candidate.class, candidateId);
            if (candidate == null) throw new CandidateNotFoundException(candidateId);

            // Validate Election
            Election election = session.get(Election.class, electionId);
            if (election == null) throw new ElectionNotFoundException(electionId);

            // Check election is ACTIVE
            if (!"ACTIVE".equalsIgnoreCase(election.getStatus())) {
                System.out.println("❌ Election is not active!");
                return;
            }

            // Check for duplicate vote (same aadhar + same election)
            Long count = (Long) session.createQuery(
                    "SELECT COUNT(v) FROM Votes v WHERE v.aadhar.aadharId=:aid AND v.election.electionId=:eid")
                    .setParameter("aid", aadharId)
                    .setParameter("eid", electionId)
                    .uniqueResult();

            if (count != null && count > 0) throw new DuplicateVoteException();

            tx = session.beginTransaction();
            Votes vote = new Votes(aadhar, candidate, election);
            session.save(vote);
            tx.commit();
            System.out.println("✅ Vote cast successfully! Vote ID: " + vote.getVoteId());

        } catch (AadharNotFoundException | CandidateNotFoundException |
                 ElectionNotFoundException | DuplicateVoteException e) {
            if (tx != null) tx.rollback();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Error casting vote: " + e.getMessage());
        }
    }

    @Override
    public void viewVotesByElection(int electionId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Votes> list = session.createQuery(
                    "FROM Votes WHERE election.electionId=:eid", Votes.class)
                    .setParameter("eid", electionId)
                    .list();
            if (list.isEmpty()) { System.out.println("No votes for this election."); return; }
            list.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public void viewAllVotes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Votes> list = session.createQuery("FROM Votes", Votes.class).list();
            if (list.isEmpty()) { System.out.println("No votes found."); return; }
            list.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}