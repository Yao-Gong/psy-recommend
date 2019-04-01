package persistence;

import domain.Quality;
/**@author Richard **/
public interface QualityDAO {
	/**@return Quality object matching the uid from database **/
	public Quality getQuality(String uid);
	/**@param quality : legal Quality object**/
	public void insertQuality(Quality quality);
	/**@param quality : legal Quality object**/
	public void updateQuality(Quality quality);
	/**@param quality : legal Quality object**/
	public void deleteQuality(Quality quality);
}
