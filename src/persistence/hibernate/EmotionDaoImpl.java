package persistence.hibernate;

import java.util.List;

import org.hibernate.Session;

import domain.Emotion;
import persistence.EmotionDAO;

/**
 * DAO object for domain model class Emotion.
 * 
 * @see domain.Emotion
 * @author Richard
 */
public class EmotionDaoImpl implements EmotionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Emotion getEmotion(String uid) {

		Session session = HibernateUtil.getSession();

		List<Emotion> result = session.createQuery("from domain.Emotion emo where emo.uid=:uid").setString("uid", uid)
				.list();
		if (result.isEmpty())
			return null;

		return result.get(0);
	}

	@Override
	public void insertEmotion(Emotion emotion) {

		Session session = HibernateUtil.getSession();

		session.save(emotion);
	}

	@Override
	public void updateEmotion(Emotion emotion) {

		Session session = HibernateUtil.getSession();

		session.update(emotion);
	}

	@Override
	public void deleteEmotion(Emotion emotion) {

		Session session = HibernateUtil.getSession();

		session.delete(emotion);
	}
}
