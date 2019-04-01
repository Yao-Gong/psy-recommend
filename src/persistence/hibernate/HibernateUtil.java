package persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
