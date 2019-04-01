package persistence;

import java.util.List;

import domain.Weibo;
/**@author Richard **/
public interface WeiboDAO {
	
	/**@return the all weiboes matching the uid from database order by Weibo.date DESC  **/
	public List<Weibo> getWeiboes(String uid);
	/**@param weibo : legal Weibo object	 **/
	public void insertWeibo(Weibo weibo);
	/**@param weibo : legal Weibo object	 **/
	public void updateWeibo(Weibo weibo);
	/**@param weibo : legal Weibo object	 **/
	public void deleteWeibo(Weibo weibo);
	/**delete all relations of the user matching the uid**/
	public void deleteWeiboes(String uid);

}
