package com.ServiceImpl;

import com.vote.HibernateUtil;
import com.vote_Entities.Election;
import com.vote_Exception.ElectionNotFoundException;
import com.vote_Service.ElectionService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ElectionServiceImpl implements ElectionService {

    @Override
    public void createElection(Election election) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(election);
            tx.commit();
            System.out.println("✅ Election created: " + election);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Error creating election: " + e.getMessage());
        }
    }

    @Override
    public void viewElectionById(int electionId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Election e = session.get(Election.class, electionId);
            if (e == null) throw new ElectionNotFoundException(electionId);
            System.out.println(e);
        } catch (ElectionNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public void viewAllElections() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Election> list = session.createQuery("FROM Election", Election.class).list();
            if (list.isEmpty()) { System.out.println("No elections found."); return; }
            list.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public void closeElection(int electionId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Election election = session.get(Election.class, electionId);
            if (election == null) throw new ElectionNotFoundException(electionId);
            election.setStatus("CLOSED");
            session.update(election);
            tx.commit();
            System.out.println("✅ Election closed: " + election.getTitle());
        } catch (ElectionNotFoundException e) {
            if (tx != null) tx.rollback();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public Election getElectionObject(int electionId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Election.class, electionId);
        } catch (Exception e) {
            System.out.println("❌ Error fetching election: " + e.getMessage());
            return null;
        }
    }
}