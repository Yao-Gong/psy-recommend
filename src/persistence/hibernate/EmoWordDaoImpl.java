package persistence.hibernate;

import java.util.List;

import org.hibernate.Session;

import domain.EmoWord;
import persistence.EmoWordDAO;

/**
 * DAO object for domain model class EmoWord.
 * 
 * @see domain.EmoWord
 * @author Richard
 */
public class EmoWordDaoImpl implements EmoWordDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<EmoWord> getEmoWord(String word) {
		Session session = HibernateUtil.getSession();

		List<EmoWord> result = session.createQuery("from domain.EmoWord emo where emo.word=:word")
				.setString("word", word).list();
		if (result.isEmpty())
			return null;

		return result;
	}

	@Override
	public void insertEmoWord(EmoWord emoWord) {
		Session session = HibernateUtil.getSession();

		session.save(emoWord);
	}

	@Override
	public void updateEmoWord(EmoWord emoWord) {
		Session session = HibernateUtil.getSession();

		session.update(emoWord);
	}

}
