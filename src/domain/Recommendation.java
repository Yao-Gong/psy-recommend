package domain;

public class Recommendation implements java.io.Serializable,Comparable<Recommendation>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7022911505392164507L;

	private User user;
	private Double emoSim;
	private Double psySim;
	private Double similarity;
	
	public Recommendation(User user,Double emoSim,Double psySim,Double sim)
	{
		this.user = user;
		this.emoSim = emoSim;
		this.psySim = psySim;
		this.similarity = sim;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Double getEmoSim() {
		return emoSim;
	}
	public void setEmoSim(Double emoSim) {
		this.emoSim = emoSim;
	}
	public Double getPsySim() {
		return psySim;
	}
	public void setPsySim(Double psySim) {
		this.psySim = psySim;
	}
	public Double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(Double similarity) {
		this.similarity = similarity;
	}
	@Override
	public int compareTo(Recommendation o) {
		
		if( null == o.getSimilarity() && null == this.similarity)
			return 0;
		else if ( null == o.getSimilarity()) {
			return 1;
		}
		else if (null == this.getSimilarity()) {
			return -1;
		}
		else {
			return this.similarity.compareTo(o.getSimilarity());
		}
	}
}
