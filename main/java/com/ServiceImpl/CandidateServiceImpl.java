package com.ServiceImpl;

import com.vote.HibernateUtil;
import com.vote_Entities.Candidate;
import com.vote_Exception.CandidateNotFoundException;
import com.vote_Service.CandidateService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CandidateServiceImpl implements CandidateService {

    @Override
    public void addCandidate(Candidate candidate) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(candidate);
            tx.commit();
            System.out.println("✅ Candidate added: " + candidate);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Error adding candidate: " + e.getMessage());
        }
    }

    @Override
    public void viewCandidateById(int candidateId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Candidate c = session.get(Candidate.class, candidateId);
            if (c == null) throw new CandidateNotFoundException(candidateId);
            System.out.println(c);
        } catch (CandidateNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public void viewCandidatesByElection(int electionId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Candidate> list = session.createQuery(
                    "FROM Candidate WHERE election.electionId=:eid", Candidate.class)
                    .setParameter("eid", electionId)
                    .list();
            if (list.isEmpty()) { System.out.println("No candidates for this election."); return; }
            list.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public void viewAllCandidates() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Candidate> list = session.createQuery("FROM Candidate", Candidate.class).list();
            if (list.isEmpty()) { System.out.println("No candidates found."); return; }
            list.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public Candidate getCandidateObject(int candidateId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Candidate.class, candidateId);
        } catch (Exception e) {
            System.out.println("❌ Error fetching candidate: " + e.getMessage());
            return null;
        }
    }
}