package service;

import java.util.List;

import domain.Assessment;
import domain.EmoWord;
import domain.PsyWord;
import domain.Word;

/**@author Richard **/
public interface WordAnalyze {

	/**
	 * @param words : sorted by word frequency and NOT null
	 * @return the Emotion and the Psychology updated in the same time
	 * BUT the all uid and judgeTime including Emotion's and Psychology's is null*/
	public Assessment analyzeWords(List<Word> words);
	
	/**@return the all emotion words matching the word from database **/
	public List<EmoWord> getEmoWord(String word);
	/**@param emoWord : completed EmoWord object	 **/
	public void insertEmoWord(EmoWord emoWord);
	/**@param emoWord : completed EmoWord object	 **/
	public void updateEmoWord(EmoWord emoWord);
	
	/**@return the all psychology words matching the word from database **/
	public List<PsyWord> getPsyWord(String word);
	/**@param psyWord : completed PsyWord object	 **/
	public void insertPsyWord(PsyWord psyWord);
	/**@param psyWord : completed PsyWord object	 **/
	public void updatePsyWord(PsyWord psyWord);
}
