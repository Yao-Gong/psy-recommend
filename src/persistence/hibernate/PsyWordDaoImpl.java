package persistence.hibernate;

import java.util.List;

import org.hibernate.Session;

import domain.PsyWord;
import persistence.PsyWordDAO;

/**
 * DAO object for domain model class PsyWord.
 * 
 * @see domain.PsyWord
 * @author Richard
 */
public class PsyWordDaoImpl implements PsyWordDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<PsyWord> getPsyWord(String word) {
		Session session = HibernateUtil.getSession();

		List<PsyWord> result = session.createQuery("from domain.PsyWord psy where psy.word=:word")
				.setString("word", word).list();
		if (result.isEmpty())
			return null;

		return result;
	}

	@Override
	public void insertPsyWord(PsyWord psyWord) {
		Session session = HibernateUtil.getSession();

		session.save(psyWord);
	}

	@Override
	public void updatePsyWord(PsyWord psyWord) {
		Session session = HibernateUtil.getSession();

		session.update(psyWord);
	}
}
