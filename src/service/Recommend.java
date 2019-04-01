package service;

import java.util.List;

import domain.Assessment;
import domain.Recommendation;

/**@author Richard **/
public interface Recommend {

	/**
	 * @see SimilarityCalculate
	 * @param suid : the id of the source user
	 * @param tuid : the id of the target user
	 * @return a completed Recommendation object
	 *  as the result of similarity between tow users  */
	public Recommendation calSimilarity(String suid,String tuid);
	/**@return the final result list of recommendation sorted by similarity*/
	public List<Recommendation> recommend(String uid);
	/**@return a valid assessment from database OR calculate a new assessment*/
	public Assessment assess(String uid);
}
