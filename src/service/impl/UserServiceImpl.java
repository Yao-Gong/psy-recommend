package service.impl;

import java.util.List;

import domain.Relation;
import domain.User;
import domain.Weibo;
import persistence.RelationDAO;
import persistence.UserDAO;
import persistence.WeiboDAO;
import service.UserService;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	private RelationDAO relationDAO;
	private WeiboDAO weiboDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setRelationDAO(RelationDAO relationDAO) {
		this.relationDAO = relationDAO;
	}

	public void setWeiboDAO(WeiboDAO weiboDAO) {
		this.weiboDAO = weiboDAO;
	}

	@Override
	public User getUser(String uid) {
		if (!checkString(uid))
			return null;

		User user = userDAO.getUser(uid);
		return user;
	}

	@Override
	public User getUser() {
		User user = userDAO.getUser();
		return user;
	}
	
	@Override
	public void insertUser(User user) {
		if (!checkUser(user))
			return;

		userDAO.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		if (!checkUser(user))
			return;

		userDAO.updateUser(user);
	}

	@Override
	public User deleteUser(String uid) {
		if (!checkString(uid))
			return null;
		User user = userDAO.getUser(uid);
		if (checkUser(user)) {
			weiboDAO.deleteWeiboes(uid);
			relationDAO.deleteRelations(uid);
			userDAO.deleteUser(user);

			return user;
		}

		return null;
	}

	@Override
	public List<Relation> getRelation(String uid) {
		if (!checkString(uid))
			return null;

		return relationDAO.getRelation(uid);
	}

	@Override
	public void insertRelation(Relation relation) {
		if (!checkRelation(relation))
			return;

		relationDAO.insertRelation(relation);
	}

	@Override
	public void deleteRelation(Relation relation) {
		if (!checkRelation(relation))
			return;

		relationDAO.deleteRelation(relation);
	}

	@Override
	public void deleteRelations(String uid) {
		if (!checkString(uid))
			return;

		relationDAO.deleteRelations(uid);
	}

	@Override
	public List<Weibo> getWeiboes(String uid) {
		if (!checkString(uid))
			return null;

		return weiboDAO.getWeiboes(uid);
	}

	@Override
	public void insertWeibo(Weibo weibo) {
		if (!checkWeibo(weibo))
			return;

		weiboDAO.insertWeibo(weibo);
	}

	@Override
	public void updateWeibo(Weibo weibo) {
		if (!checkWeibo(weibo))
			return;

		weiboDAO.updateWeibo(weibo);
	}

	@Override
	public void deleteWeibo(Weibo weibo) {
		if (!checkWeibo(weibo))
			return;

		weiboDAO.deleteWeibo(weibo);
	}

	@Override
	public void deleteWeiboes(String uid) {
		if (!checkString(uid))
			return;

		weiboDAO.deleteWeiboes(uid);
	}

	private boolean checkString(String str) {
		if (null == str || str.trim().isEmpty())
			return false;

		return true;
	}

	private boolean checkUser(User user) {
		if (null == user || !checkString(user.getUid()) || !checkString(user.getName()))
			return false;

		return true;
	}

	private boolean checkRelation(Relation relation) {
		if (null == relation || !checkString(relation.getSuid()) || !checkString(relation.getTuid()))
			return false;

		return true;
	}

	private boolean checkWeibo(Weibo weibo) {
		if (null == weibo || !checkString(weibo.getMid()) || !checkString(weibo.getUid())
				|| !checkString(weibo.getText()))
			return false;

		return true;
	}

}
