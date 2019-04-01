package service.impl;

import java.util.HashMap;
import java.util.List;

import domain.Assessment;
import domain.EmoWord;
import domain.Emotion;
import domain.PsyWord;
import domain.Psychology;
import domain.Word;
import persistence.EmoWordDAO;
import persistence.PsyWordDAO;
import service.WordAnalyze;

public class AnalyzeTravsImpl implements WordAnalyze {

	private EmoWordDAO emoWordDAO;
	private PsyWordDAO psyWordDAO;

	public void setEmoWordDAO(EmoWordDAO emoWordDAO) {
		this.emoWordDAO = emoWordDAO;
	}

	public void setPsyWordDAO(PsyWordDAO psyWordDAO) {
		this.psyWordDAO = psyWordDAO;
	}

	private int precision;

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	@Override
	public Assessment analyzeWords(List<Word> words) {
		if (null == words || words.isEmpty())
			return null;

		Emotion emotion = calEmotion(words);
		Psychology psychology = calPsychology(words);
		if (null == emotion || null == psychology)
			return null;

		Assessment assessment = new Assessment(null, emotion, psychology, null);

		return assessment;
	}

	private Psychology calPsychology(List<Word> words) {
		double neuroticism = 0.0;
		double agreeableness = 0.0;
		double extraversion = 0.0;
		double conscientiousness = 0.0;
		double openness = 0.0;

		for (Word temp : words) {
			if (null == temp || !checkString(temp.getWord()))
				continue;

			List<PsyWord> pWord = psyWordDAO.getPsyWord(temp.getWord());
			if (null == pWord)
				continue;

			for (PsyWord ptemp : pWord) {
				if (null == ptemp)
					continue;
				switch (ptemp.getType().intern()) {
				case "Neuroticism":
					neuroticism += temp.getFrequancy() * ptemp.getIntensity() * ptemp.getPolarity() * 1.0;
					break;
				case "Agreeableness":
					agreeableness += temp.getFrequancy() * ptemp.getIntensity() * ptemp.getPolarity() * 1.0;
					break;
				case "Extraversion":
					extraversion += temp.getFrequancy() * ptemp.getIntensity() * ptemp.getPolarity() * 1.0;
					break;
				case "Conscientiousness":
					conscientiousness += temp.getFrequancy() * ptemp.getIntensity() * ptemp.getPolarity() * 1.0;
					break;
				case "Openness":
					openness += temp.getFrequancy() * ptemp.getIntensity() * ptemp.getPolarity() * 1.0;
					break;

				default:
					break;
				}
			}
		}

		double sum = Math.abs(neuroticism) + Math.abs(agreeableness) + Math.abs(extraversion)
				+ Math.abs(conscientiousness) + Math.abs(openness);
		if (Double.compare(sum, 1.0) > 0) {
			neuroticism = neuroticism / sum;
			agreeableness = agreeableness / sum;
			extraversion = extraversion / sum;
			conscientiousness = conscientiousness / sum;
			openness = openness / sum;
		}

		Psychology psychology = new Psychology(null, initNum(neuroticism), initNum(agreeableness),
				initNum(extraversion), initNum(conscientiousness), initNum(openness), null);

		return psychology;
	}

	private Emotion calEmotion(List<Word> words) {
		HashMap<String, Double> map = new HashMap<>();
		map.put("NA".intern(), Double.valueOf(0));
		map.put("NB".intern(), Double.valueOf(0));
		map.put("NC".intern(), Double.valueOf(0));
		map.put("ND".intern(), Double.valueOf(0));
		map.put("NE".intern(), Double.valueOf(0));
		map.put("NG".intern(), Double.valueOf(0));
		map.put("NH".intern(), Double.valueOf(0));
		map.put("NI".intern(), Double.valueOf(0));
		map.put("NJ".intern(), Double.valueOf(0));
		map.put("NK".intern(), Double.valueOf(0));
		map.put("NL".intern(), Double.valueOf(0));
		map.put("NN".intern(), Double.valueOf(0));
		map.put("PA".intern(), Double.valueOf(0));
		map.put("PB".intern(), Double.valueOf(0));
		map.put("PC".intern(), Double.valueOf(0));
		map.put("PD".intern(), Double.valueOf(0));
		map.put("PE".intern(), Double.valueOf(0));
		map.put("PF".intern(), Double.valueOf(0));
		map.put("PG".intern(), Double.valueOf(0));
		map.put("PH".intern(), Double.valueOf(0));
		map.put("PK".intern(), Double.valueOf(0));

		double sum = 0.0;
		for (Word temp : words) {
			if (null == temp || !checkString(temp.getWord()))
				continue;

			List<EmoWord> eWord = emoWordDAO.getEmoWord(temp.getWord());
			if (null == eWord)
				continue;

			for (EmoWord etemp : eWord) {
				if (null == etemp)
					continue;

				Double num = map.get(etemp.getEmoType().intern());
				Double add = temp.getFrequancy() * etemp.getIntensity() * etemp.getPolarity() * 1.0;
				num += add;
				map.put(etemp.getEmoType().intern(), num);
				sum += Math.abs(add);
			}
		}

		if (Double.compare(sum, 1.0) > 0) {
			Emotion emotion = new Emotion(null, initNum(map.get("NA".intern()) / sum),
					initNum(map.get("NB".intern()) / sum), initNum(map.get("NC".intern()) / sum),
					initNum(map.get("ND".intern()) / sum), initNum(map.get("NE".intern()) / sum),
					initNum(map.get("NG".intern()) / sum), initNum(map.get("NH".intern()) / sum),
					initNum(map.get("NI".intern()) / sum), initNum(map.get("NJ".intern()) / sum),
					initNum(map.get("NK".intern()) / sum), initNum(map.get("NL".intern()) / sum),
					initNum(map.get("NN".intern()) / sum), initNum(map.get("PA".intern()) / sum),
					initNum(map.get("PB".intern()) / sum), initNum(map.get("PC".intern()) / sum),
					initNum(map.get("PD".intern()) / sum), initNum(map.get("PE".intern()) / sum),
					initNum(map.get("PF".intern()) / sum), initNum(map.get("PG".intern()) / sum),
					initNum(map.get("PH".intern()) / sum), initNum(map.get("PK".intern()) / sum), null);

			return emotion;
		}

		return new Emotion(null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, null);
	}

	private boolean checkString(String str) {
		if (null == str || str.trim().isEmpty())
			return false;

		return true;
	}

	private double initNum(Double num) {
		if(num<0)
			num = (int) (num * precision-0.51) * 1.0;
		else
			num = (int) (num * precision+0.51) * 1.0;
		return num / precision * 1.0;
	}
	

	@Override
	public List<EmoWord> getEmoWord(String word) {
		if (!checkString(word))
			return null;

		return emoWordDAO.getEmoWord(word);
	}

	@Override
	public void insertEmoWord(EmoWord emoWord) {
		if (!checkEmoWord(emoWord))
			return;

		emoWordDAO.insertEmoWord(emoWord);
	}

	@Override
	public void updateEmoWord(EmoWord emoWord) {
		if (!checkEmoWord(emoWord))
			return;

		emoWordDAO.updateEmoWord(emoWord);
	}

	@Override
	public List<PsyWord> getPsyWord(String word) {
		if (!checkString(word))
			return null;

		return psyWordDAO.getPsyWord(word);
	}

	@Override
	public void insertPsyWord(PsyWord psyWord) {
		if (!checkPsyWord(psyWord))
			return;

		psyWordDAO.insertPsyWord(psyWord);
	}

	@Override
	public void updatePsyWord(PsyWord psyWord) {
		if (!checkPsyWord(psyWord))
			return;

		psyWordDAO.updatePsyWord(psyWord);
	}
	
	private boolean checkEmoWord(EmoWord emoWord) {
		if (null == emoWord || !checkString(emoWord.getWord()) || !checkString(emoWord.getType()))
			return false;

		return true;
	}

	private boolean checkPsyWord(PsyWord psyWord) {
		if (null == psyWord || !checkString(psyWord.getWord()) || !checkString(psyWord.getType()))
			return false;

		return true;
	}

}
