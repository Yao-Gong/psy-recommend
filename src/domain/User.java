package domain;

// Generated 2017-4-2 9:22:29 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5597207099130493122L;
	private String uid;
	private String name;
	private Integer province;
	private Integer city;
	private String location;
	private String url;
	private String gender;
	private Integer followersnum;
	private Integer friendsnum;
	private Integer statusesnum;
	private Integer favouritesnum;
	private Date createdAt;
	private Assessment assessment;

	public User() {
	}

	public User(String uid, String name) {
		this.uid = uid;
		this.name = name;
	}

	public User(String uid, String name, Integer province, Integer city,
			String location, String url, String gender, Integer followersnum,
			Integer friendsnum, Integer statusesnum, Integer favouritesnum,
			Date createdAt) {
		this.uid = uid;
		this.name = name;
		this.province = province;
		this.city = city;
		this.location = location;
		this.url = url;
		this.gender = gender;
		this.followersnum = followersnum;
		this.friendsnum = friendsnum;
		this.statusesnum = statusesnum;
		this.favouritesnum = favouritesnum;
		this.createdAt = createdAt;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProvince() {
		return this.province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getFollowersnum() {
		return this.followersnum;
	}

	public void setFollowersnum(Integer followersnum) {
		this.followersnum = followersnum;
	}

	public Integer getFriendsnum() {
		return this.friendsnum;
	}

	public void setFriendsnum(Integer friendsnum) {
		this.friendsnum = friendsnum;
	}

	public Integer getStatusesnum() {
		return this.statusesnum;
	}

	public void setStatusesnum(Integer statusesnum) {
		this.statusesnum = statusesnum;
	}

	public Integer getFavouritesnum() {
		return this.favouritesnum;
	}

	public void setFavouritesnum(Integer favouritesnum) {
		this.favouritesnum = favouritesnum;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
