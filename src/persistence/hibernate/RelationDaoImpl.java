package persistence.hibernate;

import java.util.List;

import org.hibernate.Session;

import domain.Relation;
import persistence.RelationDAO;

/**
 * DAO object for domain model class Relation.
 * 
 * @see domain.Relation
 * @author Richard
 */
public class RelationDaoImpl implements RelationDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Relation> getRelation(String uid) {
		Session session = HibernateUtil.getSession();

		List<Relation> result = session.createQuery("from domain.Relation re where re.suid=:uid").setString("uid", uid)
				.list();
		if (result.isEmpty())
			return null;

		return result;
	}

	@Override
	public void insertRelation(Relation relation) {
		Session session = HibernateUtil.getSession();

		session.save(relation);
	}

	@Override
	public void deleteRelation(Relation relation) {
		Session session = HibernateUtil.getSession();

		session.delete(relation);
	}

	@Override
	public void deleteRelations(String uid) {
		Session session = HibernateUtil.getSession();

		session.createQuery("delete from domain.Relation re where re.suid=:uid").setString("uid", uid);
	}
}
