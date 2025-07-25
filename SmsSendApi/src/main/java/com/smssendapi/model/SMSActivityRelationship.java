package com.smssendapi.model;

import java.io.Serializable;

public class SMSActivityRelationship implements Serializable{
	private static final long serialVersionUID = 1L;
    private int activityCode;
    private String smsContentId;
    private String senderRoleId;
    private String receiverRoleId;
    private String softHardCopyFlag;
    private String dept_cd;
    private String sub_dept_cd;
    private int slNo;


    public String getDept_cd() {
        return dept_cd;
    }

    public void setDept_cd(String dept_cd) {
        this.dept_cd = dept_cd;
    }

    public String getSub_dept_cd() {
        return sub_dept_cd;
    }

    public void setSub_dept_cd(String sub_dept_cd) {
        this.sub_dept_cd = sub_dept_cd;
    }

    public int getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(int activityCode) {
        this.activityCode = activityCode;
    }

    public String getSmsContentId() {
        return smsContentId;
    }

    public void setSmsContentId(String smsContentId) {
        this.smsContentId = smsContentId;
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

    public String getSoftHardCopyFlag() {
        return softHardCopyFlag;
    }

    public void setSoftHardCopyFlag(String softHardCopyFlag) {
        this.softHardCopyFlag = softHardCopyFlag;
    }

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    @Override
    public String toString() {
        return "SMSActivityRelationship{" + "activityCode=" + activityCode + ", smsContentId=" + smsContentId + ", senderRoleId=" + senderRoleId + ", receiverRoleId=" + receiverRoleId + ", softHardCopyFlag=" + softHardCopyFlag + ", slNo=" + slNo + '}';
    }
}
