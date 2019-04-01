package service;

import java.util.List;

import domain.Assessment;
import domain.Word;

/**@author Richard **/
public interface TextService {

	/**
	 * @see ChineseSegment
	 * @param text : ensure under the limit of word size 
	 * @return sorted by word frequency 
	 **/
	public List<Word> splitText(StringBuffer text);
	
	/**
	 * @see WordAnalyze
	 * @param words : sorted by word frequency 
	 * @return the Emotion and the Psychology updated in the same time
	 * BUT the all uid and judgeTime including Emotion's and Psychology's is null
	 **/
	public Assessment analyzeWords(List<Word> words);
}
