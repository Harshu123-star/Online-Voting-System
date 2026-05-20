package com.ServiceImpl;

import com.vote.HibernateUtil;
import com.vote_Entities.Admin;
import com.vote_Exception.AdminNotFoundException;
import com.vote_Service.AdminService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Override
    public void registerAdmin(Admin admin) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(admin);
            tx.commit();
            System.out.println("✅ Admin registered: " + admin);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public void viewAdminById(int adminId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Admin admin = session.get(Admin.class, adminId);
            if (admin == null) throw new AdminNotFoundException(adminId);
            System.out.println(admin);
        } catch (AdminNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public void viewAllAdmins() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Admin> list = session.createQuery("FROM Admin", Admin.class).list();
            if (list.isEmpty()) { System.out.println("No admins found."); return; }
            list.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @Override
    public boolean verifyLogin(String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Admin admin = session.createQuery(
                    "FROM Admin WHERE email=:e AND password=:p", Admin.class)
                    .setParameter("e", email)
                    .setParameter("p", password)
                    .uniqueResult();
            return admin != null;
        } catch (Exception e) {
            System.out.println("❌ Login error: " + e.getMessage());
            return false;
        }
    }
}