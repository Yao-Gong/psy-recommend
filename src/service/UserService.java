package service;

import java.util.List;

import domain.Relation;
import domain.User;
import domain.Weibo;

/**@author Richard **/
public interface UserService {

	/**@return completed User object matching the uid from database **/
	public User getUser(String uid);
	/**@return completed User object randomly from database **/
	public User getUser();
	/**@param user : completed User object	 **/
	public void insertUser(User user);
	/**@param user : completed User object	 **/
	public void updateUser(User user);
	/**user,relations and weiboes all will be deleted
	 * @return deleted User object matching the uid from database **/
	public User deleteUser(String uid);
	
	/**@return the all relations matching the uid from database **/
	public List<Relation> getRelation(String uid);
	/**@param relation : completed Relation object**/
	public void insertRelation(Relation relation);
	/**@param relation : completed Relation object**/
	public void deleteRelation(Relation relation);
	/**delete all relations matching the uid from database **/
	public void deleteRelations(String uid);
	
	/**@return the all weiboes matching the uid from database order by Weibo.date DESC  **/
	public List<Weibo> getWeiboes(String uid);
	/**@param weibo : completed Weibo object	 **/
	public void insertWeibo(Weibo weibo);
	/**@param weibo : completed Weibo object	 **/
	public void updateWeibo(Weibo weibo);
	/**@param weibo : completed Weibo object	 **/
	public void deleteWeibo(Weibo weibo);
	/**delete all weiboes matching the uid from database  **/
	public void deleteWeiboes(String uid);
}
