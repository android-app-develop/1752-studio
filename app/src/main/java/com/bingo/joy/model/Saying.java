package com.bingo.joy.model;

public class Saying {
	private int sayingId;
	private String sayingDes;
	private String sayingKey;
	private String sayingKind;
	private String sayingRemark;

	/**
	 * 
	 */
	public Saying() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param sayingDes
	 * @param sayingKey
	 * @param sayingKind
	 * @param sayingRemark
	 */
	public Saying(String sayingDes, String sayingKey,
			String sayingKind, String sayingRemark) {
		super();
		this.sayingDes = sayingDes;
		this.sayingKey = sayingKey;
		this.sayingKind = sayingKind;
		this.sayingRemark = sayingRemark;
	}



	/**
	 * @return the sayingId
	 */
	public int getSayingId() {
		return sayingId;
	}

	/**
	 * @param sayingId the sayingId to set
	 */
	public void setSayingId(int sayingId) {
		this.sayingId = sayingId;
	}

	/**
	 * @return the sayingDes
	 */
	public String getSayingDes() {
		return sayingDes;
	}

	/**
	 * @param sayingDes the sayingDes to set
	 */
	public void setSayingDes(String sayingDes) {
		this.sayingDes = sayingDes;
	}

	/**
	 * @return the sayingKey
	 */
	public String getSayingKey() {
		return sayingKey;
	}

	/**
	 * @param sayingKey the sayingKey to set
	 */
	public void setSayingKey(String sayingKey) {
		this.sayingKey = sayingKey;
	}

	/**
	 * @return the sayingKind
	 */
	public String getSayingKind() {
		return sayingKind;
	}

	/**
	 * @param sayingKind the sayingKind to set
	 */
	public void setSayingKind(String sayingKind) {
		this.sayingKind = sayingKind;
	}

	/**
	 * @return the sayingRemark
	 */
	public String getSayingRemark() {
		return sayingRemark;
	}

	/**
	 * @param sayingRemark the sayingRemark to set
	 */
	public void setSayingRemark(String sayingRemark) {
		this.sayingRemark = sayingRemark;
	}
}
