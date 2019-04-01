package persistence;

import domain.User;
/**@author Richard **/
public interface UserDAO {

	/**@return User object matching the uid from database **/
	public User getUser(String uid);
	/**@return User object randomly from database **/
	public User getUser();
	/**@param user : legal User object	 **/
	public void insertUser(User user);
	/**@param user : legal User object	 **/
	public void updateUser(User user);
	/**@param user : legal User object**/
	public void deleteUser(User user);
}
