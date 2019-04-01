package domain;

// Generated 2017-4-2 9:22:29 by Hibernate Tools 3.4.0.CR1

/**
 * PsyWord generated by hbm2java
 */
public class PsyWord implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2159936972979360525L;
	private Integer wid;
	private String word;
	private String type;
	private Integer intensity;
	private Integer polarity;

	public PsyWord() {
	}

	public PsyWord(String word, String type) {
		this.word = word;
		this.type = type;
	}

	public PsyWord(String word, String type, Integer intensity, Integer polarity) {
		this.word = word;
		this.type = type;
		this.intensity = intensity;
		this.polarity = polarity;
	}

	public Integer getWid() {
		return this.wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIntensity() {
		return this.intensity;
	}

	public void setIntensity(Integer intensity) {
		this.intensity = intensity;
	}

	public Integer getPolarity() {
		return this.polarity;
	}

	public void setPolarity(Integer polarity) {
		this.polarity = polarity;
	}

}
