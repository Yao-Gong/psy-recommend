package service.impl;

import java.util.List;

import domain.Assessment;
import domain.Word;
import service.ChineseSegment;
import service.TextService;
import service.WordAnalyze;

public class TextServiceImpl implements TextService {

	private ChineseSegment chineseSegment;
	private WordAnalyze wordAnalyze;

	public void setChineseSegment(ChineseSegment chineseSegment) {
		this.chineseSegment = chineseSegment;
	}

	public void setWordAnalyze(WordAnalyze wordAnalyze) {
		this.wordAnalyze = wordAnalyze;
	}

	@Override
	public List<Word> splitText(StringBuffer text) {
		if (null == text || text.length() == 0)
			return null;

		List<Word> list = null;
		try {
			list = chineseSegment.segmentText(text);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Assessment analyzeWords(List<Word> words) {
		if (null == words || words.isEmpty())
			return null;

		Assessment assessment = wordAnalyze.analyzeWords(words);

		return assessment;
	}

}
