package persistence;

import domain.Emotion;
/**@author Richard **/
public interface EmotionDAO {

	/**@return Emotion object matching the uid from database **/
	public Emotion getEmotion(String uid);
	/**@param emotion : legal Emotion object**/
	public void insertEmotion(Emotion emotion);
	/**@param emotion : legal Emotion object**/
	public void updateEmotion(Emotion emotion);
	/**@param emotion : legal Emotion object**/
	public void deleteEmotion(Emotion emotion);
}
