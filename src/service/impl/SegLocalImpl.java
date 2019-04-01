package service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import domain.Word;
import edu.hit.ir.ltp4j.Postagger;
import edu.hit.ir.ltp4j.Segmentor;
import edu.hit.ir.ltp4j.SplitSentence;
import service.ChineseSegment;

public class SegLocalImpl implements ChineseSegment {

	private String segmentModel;
	private String postagModel;

	@Override
	public List<Word> segmentText(StringBuffer text) throws Exception {
		List<Word> result = new LinkedList<>();
		HashMap<String, Word> map = new HashMap<>();

		try {
			ArrayList<String> sents = new ArrayList<String>();
			SplitSentence.splitSentence(text.toString(), sents);
			for (int m = 0; m < sents.size(); m++) {
				ArrayList<String> words = new ArrayList<String>();
				ArrayList<String> postags = new ArrayList<String>();

				Segmentor.segment(sents.get(m), words);
				Postagger.postag(words, postags);
				for (int i = 0; i < words.size(); i++) {
					if (postags.get(i).equals("wp".intern()) || postags.get(i).equals("ws".intern()))
						continue;
					String w = words.get(i).intern();
					String p = postags.get(i).intern();
					Word temp = map.get(w);
					if (null == temp) {
						temp = new Word(w, Integer.valueOf(1));
						temp.setType(p);

						result.add(temp);
						map.put(w, temp);
					} else {
						temp.addFrequancy();
					}
				}
			}
		} catch (Exception e) {
			Segmentor.release();
			Postagger.release();
			throw new Exception("Local Chinese Word Segment Failed!");
		}
		
		Collections.sort(result);
		Collections.reverse(result);

		return result;
	}

	public void setSegmentModel(String segmentModel) {
		this.segmentModel = segmentModel;

		try {
			Segmentor.create(this.segmentModel);
		} catch (IllegalArgumentException e) {

		}
	}

	public void setPostagModel(String postagModel) {
		this.postagModel = postagModel;

		try {
			Postagger.create(this.postagModel);
		} catch (IllegalArgumentException e) {

		}
	}

}
