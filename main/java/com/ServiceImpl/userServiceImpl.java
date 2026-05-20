package com.ServiceImpl;

import com.vote.HibernateUtil;
import com.vote_Entities.User;
import com.vote_Exception.UserNotFoundException;
import com.vote_Service.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class userServiceImpl implements UserService {

    @Override
    public void registerUser(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            System.out.println("✅ User registered: " + user);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Error registering user: " + e.getMessage());
        }
    }

    @Override
    public void viewUserById(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, userId);
            if (user == null) throw new UserNotFoundException(userId);
            System.out.println(user);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public void viewAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<User> list = session.createQuery("FROM User", User.class).list();
            if (list.isEmpty()) { System.out.println("No users found."); return; }
            list.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public User getUserObject(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, userId);
        } catch (Exception e) {
            System.out.println("❌ Error fetching user: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean verifyLogin(String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.createQuery(
                    "FROM User WHERE email=:e AND password=:p", User.class)
                    .setParameter("e", email)
                    .setParameter("p", password)
                    .uniqueResult();
            return user != null;
        } catch (Exception e) {
            System.out.println("❌ Login error: " + e.getMessage());
            return false;
        }
    }
}