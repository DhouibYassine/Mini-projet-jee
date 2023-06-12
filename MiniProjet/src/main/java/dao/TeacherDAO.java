package dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.HibernateUtil;
import tn.iit.models.Teacher;

public class TeacherDAO {

    private SessionFactory sessionFactory;

    public TeacherDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void save(Teacher teacher) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.persist(teacher);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Teacher teacher) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.merge(teacher);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public Teacher getTeacherById(int id) {
    	 Session session = sessionFactory.getCurrentSession();
    	    Transaction transaction = null;
    	    try {
    	        transaction = session.beginTransaction();
    	        Teacher teacher = session.get(Teacher.class, id);
    	        transaction.commit();
    	        return teacher;
    	    } catch (Exception e) {
    	        if (transaction != null) {
    	            transaction.rollback();
    	        }
    	        e.printStackTrace();
    	        return null;
    	    }
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, id);
            System.out.println(teacher.getName());
            if (teacher != null) {
            	System.out.println("test");
                session.delete(teacher);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Teacher> getAllTeachers() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            TypedQuery<Teacher> query = entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
    // Add other methods for CRUD operations and queries as needed

    // ...
}
