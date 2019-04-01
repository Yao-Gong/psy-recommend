package persistence.hibernate;

import java.util.List;

import org.hibernate.Session;

import domain.Quality;
import persistence.QualityDAO;

/**
 * DAO object for domain model class Quality.
 * 
 * @see domain.Quality
 * @author Richard
 */
public class QualityDaoImpl implements QualityDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Quality getQuality(String uid) {

		Session session = HibernateUtil.getSession();

		List<Quality> result = session.createQuery("from domain.Quality qua where qua.uid=:uid").setString("uid", uid)
				.list();
		if (result.isEmpty())
			return null;

		return result.get(0);
	}

	@Override
	public void insertQuality(Quality quality) {
		Session session = HibernateUtil.getSession();

		session.save(quality);
	}

	@Override
	public void updateQuality(Quality quality) {
		Session session = HibernateUtil.getSession();

		session.update(quality);
	}

	@Override
	public void deleteQuality(Quality quality) {
		Session session = HibernateUtil.getSession();

		session.delete(quality);
	}

}
