package dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import tn.iit.models.Authorization;
import tn.iit.models.Teacher;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

public class AuthorizationDAO {
    private SessionFactory sessionFactory;

    public AuthorizationDAO() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public void addAuthorization(Authorization authorization) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(authorization);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateAuthorization(Authorization authorization) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(authorization);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteAuthorization(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Authorization authorization = session.get(Authorization.class, id);
            if (authorization != null) {
                session.delete(authorization);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Authorization getAuthorizationById(int id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Authorization.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    public List<Authorization> getAuthorizationsByMonth(Date month) {
        Session session = sessionFactory.openSession();
        Query<Authorization> query = session.createQuery("FROM Authorization WHERE EXTRACT(YEAR_MONTH FROM date) = EXTRACT(YEAR_MONTH FROM :month)", Authorization.class);
        query.setParameter("month", month);
        List<Authorization> authorizations = query.getResultList();
        session.close();
        return authorizations;
    }


    public List<Authorization> getAuthorizationsByDate(Date date) {
        Session session = sessionFactory.openSession();
        Query<Authorization> query = session.createQuery("FROM Authorization WHERE date = :date", Authorization.class);
        query.setParameter("date", date);
        List<Authorization> authorizations = query.getResultList();
        session.close();
        return authorizations;
    }

    public List<Authorization> getAllAuthorizations() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("FROM Authorization", Authorization.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

	public List<Authorization> getAuthorizationsByTeacher(Teacher teacher) {
		return teacher.getAuthorizationList();
	}
}
