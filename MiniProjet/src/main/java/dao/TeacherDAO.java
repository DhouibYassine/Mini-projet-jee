package dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

    public Teacher read(int id) {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.get(Teacher.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, id);
            if (teacher != null) {
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

    // Add other methods for CRUD operations and queries as needed

    // ...
}
