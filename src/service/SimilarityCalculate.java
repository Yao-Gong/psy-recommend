package service;

import domain.Emotion;
import domain.Psychology;

/**@author Richard **/
public interface SimilarityCalculate {

	/**
	 * @param psychology : completed Psychology object and NOT null 
	 * @param psychology2 : completed Psychology object and NOT null
	 * @return the double value of cosine similarity
	 * */
	public double calPsySim(Psychology psychology, Psychology psychology2) ;
	/**
	 * @param emotion : completed Emotion object and NOT null 
	 * @param emotion2 : completed Emotion object and NOT null
	 * @return the double value of cosine similarity
	 * */
	public double calEmoSim(Emotion emotion, Emotion emotion2);
}
