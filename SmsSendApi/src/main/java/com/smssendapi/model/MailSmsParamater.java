package com.smssendapi.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class MailSmsParamater {

	private String pension_appln_no;
	private String emp_name;
	private String emp_id;
	private String sender_user_id;
	private String senderUserName;
	private String receiver_user_id;
	private boolean acknowledge;
	private String deptCode;
	private String penSubTypeCode;
	private boolean resubmit = false;
	private String receiver_role_id;

	private String user_id;

	private String fresh_revision;

	/**
	 * @return the pension_appln_no
	 */
	public String getPension_appln_no() {
		return pension_appln_no;
	}

	/**
	 * @param pension_appln_no the pension_appln_no to set
	 */
	public void setPension_appln_no(String pension_appln_no) {
		this.pension_appln_no = pension_appln_no;
	}

	/**
	 * @return the emp_name
	 */
	public String getEmp_name() {
		return emp_name;
	}

	/**
	 * @param emp_name the emp_name to set
	 */
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	/**
	 * @return the emp_id
	 */
	public String getEmp_id() {
		return emp_id;
	}

	/**
	 * @param emp_id the emp_id to set
	 */
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	/**
	 * @return the sender_user_id
	 */
	public String getSender_user_id() {
		return sender_user_id;
	}

	/**
	 * @param sender_user_id the sender_user_id to set
	 */
	public void setSender_user_id(String sender_user_id) {
		this.sender_user_id = sender_user_id;
	}

	/**
	 * @return the senderUserName
	 */
	public String getSenderUserName() {
		return senderUserName;
	}

	/**
	 * @param senderUserName the senderUserName to set
	 */
	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

	/**
	 * @return the receiver_user_id
	 */
	public String getReceiver_user_id() {
		return receiver_user_id;
	}

	/**
	 * @param receiver_user_id the receiver_user_id to set
	 */
	public void setReceiver_user_id(String receiver_user_id) {
		this.receiver_user_id = receiver_user_id;
	}

	/**
	 * @return the acknowledge
	 */
	public boolean isAcknowledge() {
		return acknowledge;
	}

	/**
	 * @param acknowledge the acknowledge to set
	 */
	public void setAcknowledge(boolean acknowledge) {
		this.acknowledge = acknowledge;
	}

	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return the penSubTypeCode
	 */
	public String getPenSubTypeCode() {
		return penSubTypeCode;
	}

	/**
	 * @param penSubTypeCode the penSubTypeCode to set
	 */
	public void setPenSubTypeCode(String penSubTypeCode) {
		this.penSubTypeCode = penSubTypeCode;
	}

	/**
	 * @return the resubmit
	 */
	public boolean isResubmit() {
		return resubmit;
	}

	/**
	 * @param resubmit the resubmit to set
	 */
	public void setResubmit(boolean resubmit) {
		this.resubmit = resubmit;
	}

	/**
	 * @return the receiver_role_id
	 */
	public String getReceiver_role_id() {
		return receiver_role_id;
	}

	/**
	 * @param receiver_role_id the receiver_role_id to set
	 */
	public void setReceiver_role_id(String receiver_role_id) {
		this.receiver_role_id = receiver_role_id;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the fresh_revision
	 */
	public String getFresh_revision() {
		return fresh_revision;
	}

	/**
	 * @param fresh_revision the fresh_revision to set
	 */
	public void setFresh_revision(String fresh_revision) {
		this.fresh_revision = fresh_revision;
	}

	@Override
	public String toString() {
		return "MailSmsParamater [pension_appln_no=" + pension_appln_no + ", emp_name=" + emp_name + ", emp_id="
				+ emp_id + ", sender_user_id=" + sender_user_id + ", senderUserName=" + senderUserName
				+ ", receiver_user_id=" + receiver_user_id + ", acknowledge=" + acknowledge + ", deptCode=" + deptCode
				+ ", penSubTypeCode=" + penSubTypeCode + ", resubmit=" + resubmit + ", receiver_role_id="
				+ receiver_role_id + ", user_id=" + user_id + ", fresh_revision=" + fresh_revision + "]";
	}


	


	

	
}
