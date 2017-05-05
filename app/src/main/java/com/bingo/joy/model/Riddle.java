package com.bingo.joy.model;

public class Riddle {
	private int riddleId;
	private String riddleDes;
	private String riddleKey;
	private String riddleKind;
	private String riddleRemark;

	/**
	 * 
	 */
	public Riddle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param riddleId
	 * @param riddleDes
	 * @param riddleKey
	 * @param riddleKind
	 * @param riddleRemark
	 */
	public Riddle(String riddleDes, String riddleKey, String riddleKind,
			String riddleRemark) {
		super();
		// this.riddleId =this.getRiddleId();
		this.riddleDes = riddleDes;
		this.riddleKey = riddleKey;
		this.riddleKind = riddleKind;
		this.riddleRemark = riddleRemark;
	}

	/**
	 * @return the riddleId
	 */
	public int getRiddleId() {
		return riddleId;
	}

	/**
	 * @param riddleId
	 *            the riddleId to set
	 */
	public void setRiddleId(int riddleId) {
		this.riddleId = riddleId;
	}

	/**
	 * @return the riddleDes
	 */
	public String getRiddleDes() {
		return riddleDes;
	}

	/**
	 * @param riddleDes
	 *            the riddleDes to set
	 */
	public void setRiddleDes(String riddleDes) {
		this.riddleDes = riddleDes;
	}

	/**
	 * @return the riddleKey
	 */
	public String getRiddleKey() {
		return riddleKey;
	}

	/**
	 * @param riddleKey
	 *            the riddleKey to set
	 */
	public void setRiddleKey(String riddleKey) {
		this.riddleKey = riddleKey;
	}

	/**
	 * @return the riddleKind
	 */
	public String getRiddleKind() {
		return riddleKind;
	}

	/**
	 * @param riddleKind
	 *            the riddleKind to set
	 */
	public void setRiddleKind(String riddleKind) {
		this.riddleKind = riddleKind;
	}

	/**
	 * @return the riddleRemark
	 */
	public String getRiddleRemark() {
		return riddleRemark;
	}

	/**
	 * @param riddleRemark
	 *            the riddleRemark to set
	 */
	public void setRiddleRemark(String riddleRemark) {
		this.riddleRemark = riddleRemark;
	}

}
