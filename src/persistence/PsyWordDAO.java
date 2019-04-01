package persistence;

import java.util.List;

import domain.PsyWord;
/**@author Richard **/
public interface PsyWordDAO {

	/**@return the all psychology words matching the word from database **/
	public List<PsyWord> getPsyWord(String word);
	/**@param psyWord : legal PsyWord object	 **/
	public void insertPsyWord(PsyWord psyWord);
	/**@param psyWord : legal PsyWord object	 **/
	public void updatePsyWord(PsyWord psyWord);
}
