package service;

import domain.Assessment;
import domain.Emotion;
import domain.Psychology;

/**@author Richard **/
public interface AssessService {

	/**@return completed Emotion and Psychology objects matching the uid from database **/
	public Assessment getAssess(String uid);
	/**@param assessment : completed Assessment object	 **/
	public void saveAssess(Assessment assessment);
	/**@return deleted Emotion and Psychology objects matching the uid from database **/
	public Assessment deletaAssess(String uid);
	
	/**@return completed Emotion object matching the uid from database **/
	public Emotion getEmotion(String uid);
	/**@return completed Psychology object matching the uid from database **/
	public Psychology getPsychology(String uid);
}
