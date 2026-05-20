package com.ServiceImpl;

import com.vote.HibernateUtil;
import com.vote_Entities.Aadhar;
import com.vote_Exception.AadharNotFoundException;
import com.vote_Service.AadharService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AadharServiceImpl implements AadharService {

	@Override
	public void registerAadhar(Aadhar aadhar) {

	    Session session = null;
	    Transaction tx = null;

	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        tx = session.beginTransaction();

	        // ✅ Check if Aadhar already exists for user
	        Aadhar existing = session.createQuery(
	                "FROM Aadhar WHERE user.userId = :uid", Aadhar.class)
	                .setParameter("uid", aadhar.getUser().getUserId())
	                .uniqueResult();

	        if (existing != null) {
	            System.out.println("⚠️ Aadhar already exists for this user!");
	            return; // STOP execution
	        }

	        // ✅ Save if not exists
	        session.save(aadhar);
	        tx.commit();

	        System.out.println("✅ Aadhar registered successfully!");

	    } catch (Exception e) {

	        if (tx != null) tx.rollback();

	        System.out.println("❌ Error registering Aadhar: " + e.getMessage());

	    } finally {
	        if (session != null) session.close();
	    }
	}

    @Override
    public void getAadharById(int aadharId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Aadhar aadhar = session.get(Aadhar.class, aadharId);
            if (aadhar == null) throw new AadharNotFoundException(aadharId);
            System.out.println(aadhar);
        } catch (AadharNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public void getAadharByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Aadhar aadhar = session.createQuery(
                    "FROM Aadhar WHERE user.userId=:uid", Aadhar.class)
                    .setParameter("uid", userId)
                    .uniqueResult();
            if (aadhar == null) throw new AadharNotFoundException("❌ Aadhar not found for User ID: " + userId);
            System.out.println(aadhar);
        } catch (AadharNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public void viewAllAadhars() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Aadhar> list = session.createQuery("FROM Aadhar", Aadhar.class).list();
            if (list.isEmpty()) { System.out.println("No Aadhar records found."); return; }
            list.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public Aadhar getAadharObject(int aadharId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Aadhar.class, aadharId);
        } catch (Exception e) {
            System.out.println("❌ Error fetching Aadhar: " + e.getMessage());
            return null;
        }
    }
}
