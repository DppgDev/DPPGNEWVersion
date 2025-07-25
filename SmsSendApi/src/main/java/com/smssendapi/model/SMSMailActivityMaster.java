package com.smssendapi.model;

public class SMSMailActivityMaster {
	 private String activityCode;
	    private String senderRoleId;
	    private String receiverRoleId;
	    private boolean acknoledge;
	    private boolean resubmit;
	    private String deptCode;
	    private String subDeptCode;
	    private String freshRevision;
	    private String smsContent;
	    private String variableFieldSql;
	    private String mailContent;
	    private String variableFieldSqlMail;
	    private String dltTemplateId;
		public String getActivityCode() {
			return activityCode;
		}
		public void setActivityCode(String activityCode) {
			this.activityCode = activityCode;
		}
		public String getSenderRoleId() {
			return senderRoleId;
		}
		public void setSenderRoleId(String senderRoleId) {
			this.senderRoleId = senderRoleId;
		}
		public String getReceiverRoleId() {
			return receiverRoleId;
		}
		public void setReceiverRoleId(String receiverRoleId) {
			this.receiverRoleId = receiverRoleId;
		}
		public boolean isAcknoledge() {
			return acknoledge;
		}
		public void setAcknoledge(boolean acknoledge) {
			this.acknoledge = acknoledge;
		}
		public boolean isResubmit() {
			return resubmit;
		}
		public void setResubmit(boolean resubmit) {
			this.resubmit = resubmit;
		}
		public String getDeptCode() {
			return deptCode;
		}
		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}
		public String getSubDeptCode() {
			return subDeptCode;
		}
		public void setSubDeptCode(String subDeptCode) {
			this.subDeptCode = subDeptCode;
		}
		public String getFreshRevision() {
			return freshRevision;
		}
		public void setFreshRevision(String freshRevision) {
			this.freshRevision = freshRevision;
		}
		public String getSmsContent() {
			return smsContent;
		}
		public void setSmsContent(String smsContent) {
			this.smsContent = smsContent;
		}
		public String getVariableFieldSql() {
			return variableFieldSql;
		}
		public void setVariableFieldSql(String variableFieldSql) {
			this.variableFieldSql = variableFieldSql;
		}
		public String getMailContent() {
			return mailContent;
		}
		public void setMailContent(String mailContent) {
			this.mailContent = mailContent;
		}
		public String getVariableFieldSqlMail() {
			return variableFieldSqlMail;
		}
		public void setVariableFieldSqlMail(String variableFieldSqlMail) {
			this.variableFieldSqlMail = variableFieldSqlMail;
		}
		public String getDltTemplateId() {
			return dltTemplateId;
		}
		public void setDltTemplateId(String dltTemplateId) {
			this.dltTemplateId = dltTemplateId;
		}
		@Override
		public String toString() {
			return "SMSMailActivityMaster [activityCode=" + activityCode + ", senderRoleId=" + senderRoleId
					+ ", receiverRoleId=" + receiverRoleId + ", acknoledge=" + acknoledge + ", resubmit=" + resubmit
					+ ", deptCode=" + deptCode + ", subDeptCode=" + subDeptCode + ", freshRevision=" + freshRevision
					+ ", smsContent=" + smsContent + ", variableFieldSql=" + variableFieldSql + ", mailContent="
					+ mailContent + ", variableFieldSqlMail=" + variableFieldSqlMail + ", dltTemplateId="
					+ dltTemplateId + "]";
		}
	    
}
