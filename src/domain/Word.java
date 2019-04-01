package domain;

public class Word implements java.io.Serializable , Comparable<Word>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8462589120424365938L;
	private String word;
	private String type;
	private Integer frequancy;

	public Word(String word,Integer frequance)
	{
		this.word = word;
		this.frequancy = frequance;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getFrequancy() {
		return frequancy;
	}
	public void setFrequancy(Integer frequancy) {
		this.frequancy = frequancy;
	}
	
	public void addFrequancy() {
		this.frequancy = frequancy+1;
	}

	@Override
	public int compareTo(Word o) {
		
		if( null == o.getFrequancy() && null == this.frequancy)
			return 0;
		else if ( null == o.getFrequancy()) {
			return 1;
		}
		else if (null == this.frequancy) {
			return -1;
		}
		else {
			return this.frequancy.compareTo(o.getFrequancy());
		}
	}

}
