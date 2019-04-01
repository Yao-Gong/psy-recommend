package domain;

// Generated 2017-4-21 15:56:50 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Emotion generated by hbm2java
 */
public class Emotion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4328048417019867217L;
	private String uid;
	private Double na;
	private Double nb;
	private Double nc;
	private Double nd;
	private Double ne;
	private Double ng;
	private Double nh;
	private Double ni;
	private Double nj;
	private Double nk;
	private Double nl;
	private Double nn;
	private Double pa;
	private Double pb;
	private Double pc;
	private Double pd;
	private Double pe;
	private Double pf;
	private Double pg;
	private Double ph;
	private Double pk;
	private Date judgeTime;

	public Emotion() {
	}

	public Emotion(String uid, Date judgeTime) {
		this.uid = uid;
		this.judgeTime = judgeTime;
	}

	public Emotion(String uid, Double na, Double nb, Double nc, Double nd,
			Double ne, Double ng, Double nh, Double ni, Double nj, Double nk,
			Double nl, Double nn, Double pa, Double pb, Double pc, Double pd,
			Double pe, Double pf, Double pg, Double ph, Double pk,
			Date judgeTime) {
		this.uid = uid;
		this.na = na;
		this.nb = nb;
		this.nc = nc;
		this.nd = nd;
		this.ne = ne;
		this.ng = ng;
		this.nh = nh;
		this.ni = ni;
		this.nj = nj;
		this.nk = nk;
		this.nl = nl;
		this.nn = nn;
		this.pa = pa;
		this.pb = pb;
		this.pc = pc;
		this.pd = pd;
		this.pe = pe;
		this.pf = pf;
		this.pg = pg;
		this.ph = ph;
		this.pk = pk;
		this.judgeTime = judgeTime;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Double getNa() {
		return this.na;
	}

	public void setNa(Double na) {
		this.na = na;
	}

	public Double getNb() {
		return this.nb;
	}

	public void setNb(Double nb) {
		this.nb = nb;
	}

	public Double getNc() {
		return this.nc;
	}

	public void setNc(Double nc) {
		this.nc = nc;
	}

	public Double getNd() {
		return this.nd;
	}

	public void setNd(Double nd) {
		this.nd = nd;
	}

	public Double getNe() {
		return this.ne;
	}

	public void setNe(Double ne) {
		this.ne = ne;
	}

	public Double getNg() {
		return this.ng;
	}

	public void setNg(Double ng) {
		this.ng = ng;
	}

	public Double getNh() {
		return this.nh;
	}

	public void setNh(Double nh) {
		this.nh = nh;
	}

	public Double getNi() {
		return this.ni;
	}

	public void setNi(Double ni) {
		this.ni = ni;
	}

	public Double getNj() {
		return this.nj;
	}

	public void setNj(Double nj) {
		this.nj = nj;
	}

	public Double getNk() {
		return this.nk;
	}

	public void setNk(Double nk) {
		this.nk = nk;
	}

	public Double getNl() {
		return this.nl;
	}

	public void setNl(Double nl) {
		this.nl = nl;
	}

	public Double getNn() {
		return this.nn;
	}

	public void setNn(Double nn) {
		this.nn = nn;
	}

	public Double getPa() {
		return this.pa;
	}

	public void setPa(Double pa) {
		this.pa = pa;
	}

	public Double getPb() {
		return this.pb;
	}

	public void setPb(Double pb) {
		this.pb = pb;
	}

	public Double getPc() {
		return this.pc;
	}

	public void setPc(Double pc) {
		this.pc = pc;
	}

	public Double getPd() {
		return this.pd;
	}

	public void setPd(Double pd) {
		this.pd = pd;
	}

	public Double getPe() {
		return this.pe;
	}

	public void setPe(Double pe) {
		this.pe = pe;
	}

	public Double getPf() {
		return this.pf;
	}

	public void setPf(Double pf) {
		this.pf = pf;
	}

	public Double getPg() {
		return this.pg;
	}

	public void setPg(Double pg) {
		this.pg = pg;
	}

	public Double getPh() {
		return this.ph;
	}

	public void setPh(Double ph) {
		this.ph = ph;
	}

	public Double getPk() {
		return this.pk;
	}

	public void setPk(Double pk) {
		this.pk = pk;
	}

	public Date getJudgeTime() {
		return this.judgeTime;
	}

	public void setJudgeTime(Date judgeTime) {
		this.judgeTime = judgeTime;
	}

}
