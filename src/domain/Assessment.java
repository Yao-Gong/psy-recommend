package domain;

import java.util.Date;

public class Assessment implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6680057007261296494L;
	private String uid;
	private Emotion emotion;
	private Psychology psychology;
	private Date judgeTime;
	private static long validTime = -1;

	public long getValidTime() {
		return validTime;
	}

	public void setValidTime(int days) {
		if (days < 1) {
			validTime = -1;
			return;
		}

		validTime = days * 24 * 3600 * 1000L;
	}

	public Assessment() {
	}

	public Assessment(String uid, Emotion emotion, Psychology psychology,Date judgeTime) {
		this.uid = uid;
		this.emotion = emotion;
		this.psychology = psychology;
		this.judgeTime = judgeTime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
		this.emotion.setUid(uid);
		this.psychology.setUid(uid);
	}

	public Emotion getEmotion() {
		return emotion;
	}

	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}

	public Psychology getPsychology() {
		return psychology;
	}

	public void setPsychology(Psychology psychology) {
		this.psychology = psychology;
	}

	public Date getJudgeTime() {
		return judgeTime;
	}

	public void setJudgeTime(Date judgeTime) {
		this.judgeTime = judgeTime;
		this.emotion.setJudgeTime(judgeTime);
		this.psychology.setJudgeTime(judgeTime);
	}
	
	public boolean isValid()
	{
		if(null == judgeTime)
			return false;
		else if(-1 == validTime)
			return true;
		else if((new Date().getTime()-judgeTime.getTime())<=validTime)
			return true;
		
		return false;
	}
	
	public boolean check()
	{
		if( null == this.emotion || null == this.psychology)
			return false;
		
		return true;
	}

}
