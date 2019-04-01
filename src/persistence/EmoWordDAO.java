package persistence;

import java.util.List;

import domain.EmoWord;
/**@author Richard **/
public interface EmoWordDAO {

	/**@return the all emotion words matching the word from database **/
	public List<EmoWord> getEmoWord(String word);
	/**@param emoWord : legal EmoWord object	 **/
	public void insertEmoWord(EmoWord emoWord);
	/**@param emoWord : legal EmoWord object	 **/
	public void updateEmoWord(EmoWord emoWord);
}
