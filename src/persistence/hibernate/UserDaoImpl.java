package persistence.hibernate;

import java.util.List;
import java.util.Random;

import org.hibernate.Session;

import domain.User;
import persistence.UserDAO;

/**
 * DAO object for domain model class User.
 * 
 * @see domain.User
 * @author Richard
 */
public class UserDaoImpl implements UserDAO {

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String uid) {
		Session session = HibernateUtil.getSession();

		List<User> result = session.createQuery("from domain.User user where user.uid=:uid").setString("uid", uid)
				.list();
		if (result.isEmpty())
			return null;

		return result.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User getUser() {
		Session session = HibernateUtil.getSession();

		String uid;
		List<User> result;
		int loop = 10;
		do {
			uid = "%" + String.valueOf(new Random().nextInt(100));
			result = session.createQuery("from domain.User user where user.uid like :uid").setString("uid", uid).list();
		} while (result.isEmpty() && loop-- > 0);

		if (result.isEmpty())
			return null;

		return result.get(0);
	}

	@Override
	public void insertUser(User user) {
		Session session = HibernateUtil.getSession();

		session.save(user);
	}

	@Override
	public void updateUser(User user) {
		Session session = HibernateUtil.getSession();

		session.update(user);
	}

	@Override
	public void deleteUser(User user) {
		Session session = HibernateUtil.getSession();

		session.delete(user);
	}
}
