package service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import domain.Assessment;
import domain.Recommendation;
import domain.Relation;
import domain.User;
import domain.Weibo;
import domain.Word;
import service.AssessService;
import service.Recommend;
import service.SimilarityCalculate;
import service.TextService;
import service.UserService;

public class RecommendImpl implements Recommend {

	protected AssessService assessService;
	protected TextService textService;
	protected UserService userService;
	protected SimilarityCalculate similarityCalculate;

	public void setAssessService(AssessService assessService) {
		this.assessService = assessService;
	}

	public void setTextService(TextService textService) {
		this.textService = textService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setSimilarityCalculate(SimilarityCalculate similarityCalculate) {
		this.similarityCalculate = similarityCalculate;
	}

	private double emoWeight;
	private double psyWeight;
	private int weiboSize;
	private int textLength;

	public void setEmoWeight(double emoWeight) {
		this.emoWeight = emoWeight;
	}

	public void setPsyWeight(double psyWeight) {
		this.psyWeight = psyWeight;
	}

	public void setWeiboSize(int weiboSize) {
		this.weiboSize = weiboSize;
	}

	public void setTextLength(int textLength) {
		this.textLength = textLength;
	}

	@Override
	public Recommendation calSimilarity(String suid, String tuid) {
		User tUser = userService.getUser(tuid);
		if (null == tUser)
			return null;

		Assessment uAssess = assess(suid);
		Assessment tAssess = assess(tuid);

		if (null != uAssess && null != tAssess) {
			tUser.setAssessment(tAssess);

			double emotion = similarityCalculate.calEmoSim(uAssess.getEmotion(), tAssess.getEmotion());
			double psychology = similarityCalculate.calPsySim(uAssess.getPsychology(), tAssess.getPsychology());
			double sim = emotion * emoWeight * 1.0 + psychology * psyWeight * 1.0;
			if(sim<0)
				sim = (int)(sim*10000-0.51)*1.0;
			else
				sim = (int)(sim*10000+0.51)*1.0;
			sim =  sim/10000*1.0;

			Recommendation result = new Recommendation(tUser, emotion, psychology, sim);

			return result;
		}
		return null;
	}

	@Override
	public List<Recommendation> recommend(String uid) {
		List<Relation> dirRela = userService.getRelation(uid);
		if (null == dirRela || dirRela.isEmpty())
			return null;

		HashSet<String> direct = new HashSet<>();
		direct.add(uid.intern());
		for (Relation dr : dirRela) {
			String temp = dr.getTuid().intern();
			if (direct.contains(temp))
				continue;
			direct.add(temp);
		}

		direct.remove(uid.intern());
		HashSet<String> indirect = new HashSet<>();
		for (String tuid : direct) {
			List<Relation> indir = userService.getRelation(tuid);
			if (null == indir || indir.isEmpty())
				continue;

			for (Relation in : indir) {
				String temp = in.getTuid().intern();
				if (indirect.contains(temp))
					continue;

				indirect.add(temp);
			}
		}

		indirect.removeAll(direct);
		indirect.remove(uid.intern());

		List<Recommendation> result = new LinkedList<>();
		for (String tuid : indirect) {
			Recommendation recom = calSimilarity(uid, tuid);
			if (recom != null)
				result.add(recom);
		}

		Collections.sort(result);
		Collections.reverse(result);

		return result;
	}

	@Override
	public Assessment assess(String uid) {
		Assessment assess = assessService.getAssess(uid);
		if (assess != null && assess.isValid())
			return assess;

		List<Weibo> weiboes = userService.getWeiboes(uid);
		if (null != weiboes && !weiboes.isEmpty()) {
			List<Word> words = new LinkedList<>();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < weiboes.size() && i < weiboSize; i++) {
				String temp = weiboes.get(i).getText().trim().split("//@")[0];
				if (null != temp && !temp.trim().isEmpty())
				{
					if((buffer.length()+temp.length())>textLength)
					{
						List<Word> tWords= textService.splitText(buffer);
						if(null != tWords)
							words.addAll(tWords);
						buffer.setLength(0);
					}
					buffer.append(temp);
				}					
			}

			List<Word> tWords= textService.splitText(buffer);
			if(null != tWords)
				words.addAll(tWords);
			assess = textService.analyzeWords(words);
			
			if(null != assess )
			{
				assess.setUid(uid);
				assess.setJudgeTime(new Date());
				assessService.saveAssess(assess);
			}
		
			return assess;
		}

		return null;
	}

}
