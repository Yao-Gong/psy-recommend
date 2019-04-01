package service;

import java.util.List;

import domain.Word;

/** @author Richard **/
public interface ChineseSegment {

	/**
	 * @param text : ensure under the limit of word size and NOT null
	 * @return sorted by word frequency
	 */
	public List<Word> segmentText(StringBuffer text) throws Exception;
}
