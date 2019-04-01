package service.impl;

import domain.Emotion;
import domain.Psychology;
import service.SimilarityCalculate;

public class CalSimCosImpl implements SimilarityCalculate {
	
	private int precision;

	@Override
	public double calPsySim(Psychology psychology, Psychology psychology2) {
		double length1 = Math.pow(psychology.getAgreeableness(), 2) + Math.pow(psychology.getConscientiousness(), 2)
				+ Math.pow(psychology.getExtraversion(), 2) + Math.pow(psychology.getNeuroticism(), 2)
				+ Math.pow(psychology.getOpenness(), 2);
		double length2 = Math.pow(psychology2.getAgreeableness(), 2) + Math.pow(psychology2.getConscientiousness(), 2)
				+ Math.pow(psychology2.getExtraversion(), 2) + Math.pow(psychology2.getNeuroticism(), 2)
				+ Math.pow(psychology2.getOpenness(), 2);

		if((int)(length1*length2*precision) == 0)
			return 0.0;
		double cosin = (psychology.getAgreeableness() * psychology2.getAgreeableness()
				+ psychology.getConscientiousness() * psychology2.getConscientiousness()
				+ psychology.getExtraversion() * psychology2.getExtraversion()
				+ psychology.getNeuroticism() * psychology2.getNeuroticism()
				+ psychology.getOpenness() * psychology2.getOpenness()) / Math.sqrt(length1 * length2);

		return initNum(cosin);
	}

	@Override
	public double calEmoSim(Emotion emotion, Emotion emotion2) {
		double happy = emotion.getPa() + emotion.getPe();
		double good = emotion.getPd() + emotion.getPh() + emotion.getPg() + emotion.getPb() + emotion.getPk();
		double angry = emotion.getNa();
		double sad = emotion.getNb() + emotion.getNj() + emotion.getNh() + emotion.getPf();
		double fear = emotion.getNi() + emotion.getNc() + emotion.getNg();
		double evil = emotion.getNe() + emotion.getNd() + emotion.getNn() + emotion.getNk() + emotion.getNl();
		double shock = emotion.getPc();

		double happy2 = emotion2.getPa() + emotion2.getPe();
		double good2 = emotion2.getPd() + emotion2.getPh() + emotion2.getPg() + emotion2.getPb() + emotion2.getPk();
		double angry2 = emotion2.getNa();
		double sad2 = emotion2.getNb() + emotion2.getNj() + emotion2.getNh() + emotion2.getPf();
		double fear2 = emotion2.getNi() + emotion2.getNc() + emotion2.getNg();
		double evil2 = emotion2.getNe() + emotion2.getNd() + emotion2.getNn() + emotion2.getNk() + emotion2.getNl();
		double shock2 = emotion2.getPc();

		double length = Math.pow(happy, 2) + Math.pow(good, 2) + Math.pow(angry, 2) + Math.pow(sad, 2)
				+ Math.pow(fear, 2) + Math.pow(evil, 2) + Math.pow(shock, 2);
		double length2 = Math.pow(happy2, 2) + Math.pow(good2, 2) + Math.pow(angry2, 2) + Math.pow(sad2, 2)
				+ Math.pow(fear2, 2) + Math.pow(evil2, 2) + Math.pow(shock2, 2);

		if((int)(length*length2*precision) == 0)
			return 0.0;
		double cosin = (happy * happy2 + good * good2 + angry * angry2 + sad * sad2 + fear * fear2 + evil * evil2
				+ shock * shock2) / Math.sqrt(length * length2);

		return initNum(cosin);
	}
	
	private double initNum(Double num) {
		if(num<0)
			num = (int) (num * precision-0.51) * 1.0;
		else
			num = (int) (num * precision+0.51) * 1.0;
		return num / precision * 1.0;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

}
