package com.smssendapi.model;

public class SMSMailVariableFieldsModel {
	 private String empName;
	    private String designation;
	    private String instituteName;
	    private String diseCode;
	    private String empPermanantAddress;
	    private String applicantName;
	    private String district;
	    private String userName;
	    private String userRole;
	    private String circleName;
	    private String dor;
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		public String getInstituteName() {
			return instituteName;
		}
		public void setInstituteName(String instituteName) {
			this.instituteName = instituteName;
		}
		public String getDiseCode() {
			return diseCode;
		}
		public void setDiseCode(String diseCode) {
			this.diseCode = diseCode;
		}
		public String getEmpPermanantAddress() {
			return empPermanantAddress;
		}
		public void setEmpPermanantAddress(String empPermanantAddress) {
			this.empPermanantAddress = empPermanantAddress;
		}
		public String getApplicantName() {
			return applicantName;
		}
		public void setApplicantName(String applicantName) {
			this.applicantName = applicantName;
		}
		public String getDistrict() {
			return district;
		}
		public void setDistrict(String district) {
			this.district = district;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserRole() {
			return userRole;
		}
		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}
		public String getCircleName() {
			return circleName;
		}
		public void setCircleName(String circleName) {
			this.circleName = circleName;
		}
		public String getDor() {
			return dor;
		}
		public void setDor(String dor) {
			this.dor = dor;
		}
		@Override
		public String toString() {
			return "SMSMailVariableFieldsModel [empName=" + empName + ", designation=" + designation
					+ ", instituteName=" + instituteName + ", diseCode=" + diseCode + ", empPermanantAddress="
					+ empPermanantAddress + ", applicantName=" + applicantName + ", district=" + district
					+ ", userName=" + userName + ", userRole=" + userRole + ", circleName=" + circleName + ", dor="
					+ dor + "]";
		}
	    
}
