package com.ServiceImpl;

import com.vote.HibernateUtil;
import com.vote_Entities.*;
import com.vote_Exception.ElectionNotFoundException;
import com.vote_Service.ResultService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ResultServiceImpl implements ResultService {

    @Override
    public void computeAndDeclareResult(int electionId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Election election = session.get(Election.class, electionId);
            if (election == null) throw new ElectionNotFoundException(electionId);

            // Get vote counts per candidate for this election
            List<Object[]> counts = session.createQuery(
                    "SELECT v.candidate, COUNT(v) FROM Votes v " +
                    "WHERE v.election.electionId=:eid " +
                    "GROUP BY v.candidate ORDER BY COUNT(v) DESC")
                    .setParameter("eid", electionId)
                    .list();

            if (counts == null || counts.isEmpty()) {
                System.out.println("❌ No votes found for election: " + election.getTitle());
                return;
            }

            // First row = winner
            Object[] top = counts.get(0);
            Candidate winner = (Candidate) top[0];
            int winnerVotes = ((Long) top[1]).intValue();

            // Check if result already exists
            Result existing = session.createQuery(
                    "FROM Result WHERE election.electionId=:eid", Result.class)
                    .setParameter("eid", electionId)
                    .uniqueResult();

            tx = session.beginTransaction();
            if (existing != null) {
                existing.setWinner(winner);
                existing.setWinnerVotes(winnerVotes);
                session.update(existing);
            } else {
                Result result = new Result(election, winner, winnerVotes);
                session.save(result);
            }
            tx.commit();

            System.out.println("🏆 Result Declared!");
            System.out.println("   Election : " + election.getTitle());
            System.out.println("   Winner   : " + winner.getName() + " (" + winner.getParty() + ")");
            System.out.println("   Votes    : " + winnerVotes);

            // Print full tally
            System.out.println("\n--- Full Vote Tally ---");
            for (Object[] row : counts) {
                Candidate c = (Candidate) row[0];
                long votes = (Long) row[1];
                System.out.println("  " + c.getName() + " (" + c.getParty() + ") → " + votes + " vote(s)");
            }

        } catch (ElectionNotFoundException e) {
            if (tx != null) tx.rollback();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Error computing result: " + e.getMessage());
        }
    }

    @Override
    public void viewResult(int electionId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Result result = session.createQuery(
                    "FROM Result WHERE election.electionId=:eid", Result.class)
                    .setParameter("eid", electionId)
                    .uniqueResult();
            if (result == null) {
                System.out.println("❌ No result declared yet for Election ID: " + electionId);
                return;
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public void viewAllResults() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Result> list = session.createQuery("FROM Result", Result.class).list();
            if (list.isEmpty()) { System.out.println("No results declared yet."); return; }
            list.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}