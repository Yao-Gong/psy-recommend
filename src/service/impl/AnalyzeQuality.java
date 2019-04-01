package service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import domain.Quality;
import domain.Recommendation;
import domain.Relation;
import persistence.QualityDAO;

public class AnalyzeQuality extends RecommendImpl {
	
	protected QualityDAO qualityDAO;
	
	public void setQualityDAO(QualityDAO qualityDAO) {
		this.qualityDAO = qualityDAO;
	}
	
	public Quality getQuality(String uid)
	{
		Quality quality = qualityDAO.getQuality(uid.trim());
		if(null == quality)
		{
			quality = calQuality(uid.trim().intern());
			if(null != quality)
				qualityDAO.insertQuality(quality);
		}
		
		return quality;
	}
	
	public Quality calQuality(String uid)
	{
		List<Relation> dirRela = userService.getRelation(uid.trim().intern());
		HashSet<String> direct = new HashSet<>();
		direct.add(uid.intern());
		for (Relation dr : dirRela) {
			String temp = dr.getTuid().intern();
			if (direct.contains(temp))
				continue;
			direct.add(temp);
		}
		direct.remove(uid.intern());
		
		List<Recommendation> result = recommend(uid.trim().intern());
		if(null == result || result.isEmpty())
			return null;
		else if (result.size() == direct.size()) {
			return new Quality(uid, 100.0, 100.0);
		}
		result = new ArrayList<Recommendation>(result);
		int length = (result.size()>recomSize)?recomSize:result.size();
		int maxDir = (direct.size()>recomSize)?recomSize:direct.size();
		int num = 0;
		int sum = 0;
		for(int i =0;i<length;i++)
		{
			Recommendation temp = result.get(i);
			if(direct.contains(temp.getUser().getUid().intern()))
			{
				num++;
				sum += (length-i);
			}
		}
		
		double accuracy = 0;
		double effect = 0;
		if(num == length)
		{
			accuracy = 100.0;
			effect = 100.0;
		}
		else if(num != 0)
		{
			accuracy = initNum(100.0*num/maxDir);
			effect = initNum(100.0*sum/((length+length-num+1)*num/2.0));
		}		
		Quality quality = new Quality(uid.trim(), accuracy, effect);
		
		return quality;
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

		indirect.addAll(direct);
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
	
	private double initNum(Double num) {
		if(num<0)
			num = (int) (num * precision-0.51) * 1.0;
		else
			num = (int) (num * precision+0.51) * 1.0;
		return num / precision * 1.0;
	}
	
	private int precision;
	private int recomSize;

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public void setRecomSize(int recomSize) {
		this.recomSize = recomSize;
	}
}
