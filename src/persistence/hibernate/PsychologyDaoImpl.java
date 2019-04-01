package persistence.hibernate;

import java.util.List;

import org.hibernate.Session;

import domain.Psychology;
import persistence.PsychologyDAO;

/**
 * DAO object for domain model class Psychology.
 * 
 * @see domain.Psychology
 * @author Richard
 */
public class PsychologyDaoImpl implements PsychologyDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Psychology getPsychology(String uid) {
		Session session = HibernateUtil.getSession();

		List<Psychology> result = session.createQuery("from domain.Psychology psy where psy.uid=:uid")
				.setString("uid", uid).list();
		if (result.isEmpty())
			return null;

		return result.get(0);
	}

	@Override
	public void insertPsychology(Psychology psychology) {
		Session session = HibernateUtil.getSession();

		session.save(psychology);
	}

	@Override
	public void updatePsychology(Psychology psychology) {
		Session session = HibernateUtil.getSession();

		session.update(psychology);
	}

	@Override
	public void deletePsychology(Psychology psychology) {
		Session session = HibernateUtil.getSession();

		session.delete(psychology);
	}

}
