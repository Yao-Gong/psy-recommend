package persistence.hibernate;

import java.util.List;

import org.hibernate.Session;

import domain.Weibo;
import persistence.WeiboDAO;

/**
 * DAO object for domain model class Weibo.
 * 
 * @see domain.Weibo
 * @author Richard
 */
public class WeiboDaoImpl implements WeiboDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Weibo> getWeiboes(String uid) {
		Session session = HibernateUtil.getSession();

		List<Weibo> result = session.createQuery("from domain.Weibo weibo where weibo.uid=:uid order by weibo.date desc").setString("uid", uid)
				.list();
		if (result.isEmpty())
			return null;

		return result;
	}

	@Override
	public void insertWeibo(Weibo weibo) {
		Session session = HibernateUtil.getSession();

		session.save(weibo);;
	}

	@Override
	public void updateWeibo(Weibo weibo) {
		Session session = HibernateUtil.getSession();

		session.update(weibo);
	}

	@Override
	public void deleteWeibo(Weibo weibo) {
		Session session = HibernateUtil.getSession();

		session.delete(weibo);
	}

	@Override
	public void deleteWeiboes(String uid) {
		Session session = HibernateUtil.getSession();

		session.createQuery("delete from domain.Weibo weibo where weibo.uid=:uid").setString("uid", uid);
	}

}
