package service.impl;

import domain.Assessment;
import domain.Emotion;
import domain.Psychology;
import persistence.EmotionDAO;
import persistence.PsychologyDAO;
import service.AssessService;

public class AssessServiceImpl implements AssessService {

	private EmotionDAO emotionDAO;
	private PsychologyDAO psychologyDAO;

	public void setEmotionDAO(EmotionDAO emotionDAO) {
		this.emotionDAO = emotionDAO;
	}

	public void setPsychologyDAO(PsychologyDAO psychologyDAO) {
		this.psychologyDAO = psychologyDAO;
	}

	@Override
	public Assessment getAssess(String uid) {

		Emotion emotion = getEmotion(uid);
		if (null == emotion)
			return null;
		Psychology psychology = getPsychology(uid);
		if (null == psychology)
			return null;

		Assessment assessment = new Assessment(uid, emotion, psychology, emotion.getJudgeTime());
		return assessment;
	}
	
	@Override
	public void saveAssess(Assessment assessment) {
		if (!checkAssess(assessment))
			return;
		
		Emotion etemp = emotionDAO.getEmotion(assessment.getUid());
		if(null == etemp)
			emotionDAO.insertEmotion(assessment.getEmotion());
		else
			emotionDAO.updateEmotion(assessment.getEmotion());
		
		Psychology ptemp = psychologyDAO.getPsychology(assessment.getUid());
		if(null == ptemp)
			psychologyDAO.insertPsychology(assessment.getPsychology());
		else
			psychologyDAO.updatePsychology(assessment.getPsychology());
	}

	@Override
	public Assessment deletaAssess(String uid) {

		Assessment assessment = getAssess(uid);
		if (null == assessment)
			return null;

		emotionDAO.deleteEmotion(assessment.getEmotion());
		psychologyDAO.deletePsychology(assessment.getPsychology());
		return assessment;
	}

	@Override
	public Emotion getEmotion(String uid) {
		if (!checkUid(uid))
			return null;

		return emotionDAO.getEmotion(uid);
	}

	@Override
	public Psychology getPsychology(String uid) {
		if (!checkUid(uid))
			return null;

		return psychologyDAO.getPsychology(uid);
	}

	private boolean checkUid(String uid) {
		if (null == uid || uid.trim().isEmpty())
			return false;

		return true;
	}

	private boolean checkAssess(Assessment assessment) {
		if (null == assessment || !assessment.check())
			return false;

		return true;
	}

}
