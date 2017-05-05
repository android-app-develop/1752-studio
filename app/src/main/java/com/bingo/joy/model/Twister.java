package com.bingo.joy.model;

public class Twister {
	private int twisterId;
	private String twisterDes;
	private String twisterKey;
	private String twisterKind;
	private String twisterRemark;

	/**
	 * 
	 */
	public Twister() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * @param twisterDes
	 * @param twisterKey
	 * @param twisterKind
	 * @param twisterRemark
	 */
	public Twister(String twisterDes, String twisterKey,
			String twisterKind, String twisterRemark) {
		super();
		this.twisterDes = twisterDes;
		this.twisterKey = twisterKey;
		this.twisterKind = twisterKind;
		this.twisterRemark = twisterRemark;
	}



	/**
	 * @return the twisterId
	 */
	public int getTwisterId() {
		return twisterId;
	}

	/**
	 * @param twisterId the twisterId to set
	 */
	public void setTwisterId(int twisterId) {
		this.twisterId = twisterId;
	}

	/**
	 * @return the twisterDes
	 */
	public String getTwisterDes() {
		return twisterDes;
	}

	/**
	 * @param twisterDes the twisterDes to set
	 */
	public void setTwisterDes(String twisterDes) {
		this.twisterDes = twisterDes;
	}

	/**
	 * @return the twisterKey
	 */
	public String getTwisterKey() {
		return twisterKey;
	}

	/**
	 * @param twisterKey the twisterKey to set
	 */
	public void setTwisterKey(String twisterKey) {
		this.twisterKey = twisterKey;
	}

	/**
	 * @return the twisterKind
	 */
	public String getTwisterKind() {
		return twisterKind;
	}

	/**
	 * @param twisterKind the twisterKind to set
	 */
	public void setTwisterKind(String twisterKind) {
		this.twisterKind = twisterKind;
	}

	/**
	 * @return the twisterRemark
	 */
	public String getTwisterRemark() {
		return twisterRemark;
	}

	/**
	 * @param twisterRemark the twisterRemark to set
	 */
	public void setTwisterRemark(String twisterRemark) {
		this.twisterRemark = twisterRemark;
	}

	
}
