package com.smssendapi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smssendapi.model.UserMaster;



public class MailSmsService {
	Logger logger = LogManager.getLogger(MailSmsService.class);

	// Module for fetching Phone No & Email //
	public UserMaster getPhoneNoEmail(String userid, String table, Connection con) {
		String feild5 = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		UserMaster um = new UserMaster();
//		        System.out.println("Inside getPhoneNoEmail");
//		        System.out.println("User ID: "+userid);
		try {
			switch (table.trim()) {
			case "user_master":
				feild5 = userid.trim();
				ps = con.prepareStatement("select mobile_no, e_mail_id from user_master where user_id=?");
				ps.setString(1, feild5);
				System.out.println("Query: USER" + ps);
				rs = ps.executeQuery();
				if (rs.next()) {
					um.setMobile_no(rs.getString("mobile_no"));
					um.setE_mail_id(rs.getString("e_mail_id"));
				}

				break;
			case "emp_mast":
				// System.out.println("userId>>>>>>" + userid + "table>>>>>>>>" + table);
				feild5 = userid.trim();
				ps = con.prepareStatement("select mob_no, email_id from emp_mast where emp_id=?");
				ps.setString(1, feild5);
				System.out.println("Quer: EMP" + ps);
				logger.info("Quer: emp_mast-->" + ps.toString());
				rs = ps.executeQuery();
				if (rs.next()) {
					um.setMobile_no(rs.getString("mob_no"));
					um.setE_mail_id(rs.getString("email_id"));
				}
				break;
			case "p_data":
				feild5 = userid.trim();
				ps = con.prepareStatement("select claimant_mob_no from p_data where pension_appln_no=?");
				ps.setString(1, feild5);
				logger.info("Quer: p_data-->" + ps.toString());
				rs = ps.executeQuery();
				if (rs.next()) {
					um.setMobile_no(rs.getString("claimant_mob_no"));
					// um.setE_mail_id(rs.getString("email_id"));
				}
				break;
			case "participating_dept_emp_info":
				feild5 = userid.trim();
				ps = con.prepareStatement("select mobile_no,email_id from participating_dept_emp_info where emp_id=?");
				ps.setString(1, feild5);
//		                System.out.println("Quer: PDATA" + ps);
				logger.info("Quer: participating_dept_emp_info-->" + ps.toString());
				rs = ps.executeQuery();
				if (rs.next()) {
					um.setMobile_no(rs.getString("mobile_no"));
					um.setE_mail_id(rs.getString("email_id"));
				}
				break;

			}

		} catch (SQLException e) {
			System.out.println("ERROR---" + e);
			logger.info("ERROR-->" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				logger.info("ERROR-->" + e);

			}
		}
		return um;
	}

	public String getTemplateId(String sms_id, Connection con) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String seq = null;
		String tempid = null;

		try {

			ps = con.prepareStatement("select template_id from sms_annexure_master where TRIM(sms_annexure_id)=?");
			ps.setString(1, sms_id.trim());
			rs = ps.executeQuery();
			if (rs.next()) {

				tempid = rs.getString("template_id");
			}

		} catch (SQLException e) {
			System.out.println("exception---" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException ex) {
				System.out.println("Finally---" + ex);
			}
		}
		// return amlist;
		// System.out.println("My sequence list is " + seq);
		return tempid;
	}

	public String createMsgAppl(String sms, String roleReceiver, String applno, String dor, String empid, String otp,
			Connection con) {

		System.out.println("\nInside SMSdao createMsgAppl method with applno  parameter" + sms);
		logger.info("\\nInside SMSdao createMsgAppl method with applno  parameter-->" + sms);
		String msg = "";
		String content;
		String qrystr = "";
		String[] seqarr = null;
		PreparedStatement ps1 = null, ps2 = null;
		ResultSet rs1 = null, rs2 = null;
		SMS_annexure_masterList ob1 = new SMS_annexure_masterList();
		// System.out.println("sms->>" + sms);

		try {

			if (con != null) {

//		            SMS_annexure_master ob1=new SMS_annexure_master();
				String seqn = ob1.compareAnnexure(sms);
//		            ps=con.prepareStatement("select sequence from sms_annexure_master where sms_annexure_id=?");
//		            ps.setString(1, sms);
//		            rs=ps.executeQuery();
//		            if(rs.next()){
////		                String seq=rs.getString("sequence");
//		                seqarr=new String[seq.length()];
//		                seqarr=seq.split("#");
//		            }
				System.out.println("sms contecn sequnce--->" + seqn);
				logger.info("sms contecn sequnce--->" + seqn);
				seqarr = new String[seqn.length()];
				seqarr = seqn.split("#");
				for (String st : seqarr) {

					if (st.startsWith("C")) {
//		                    SMS_static_sub_content_masterList ob2 = new SMS_static_sub_content_masterList();
//		                    msg += ob2.compareContent(sms, st) + " ";
						ps1 = con.prepareStatement(
								"select sms_sub_content from sms_static_sub_content_master where sms_static_sub_id=?");
						// ps1.setString(1, sms);
						ps1.setString(1, st);

						rs1 = ps1.executeQuery();
						if (rs1.next()) {
							msg += rs1.getString("sms_sub_content") + " ";
							// System.out.println("sms_sub_content->>" + rs1.getString("sms_sub_content")+"
							// \nmsg content"+msg);
						}
					} else if (st.startsWith("V")) {
						content = "";
						ps1 = con.prepareStatement(
								"select var_column,var_table_name, condition from sms_variable_content_master where sms_var_id=?");
						ps1.setString(1, st.trim());

						rs1 = ps1.executeQuery();
						// System.out.println("sql-CONDITATINO->\n" + ps1.toString());
						if (rs1.next()) {
							String column = rs1.getString("var_column");
							String table = rs1.getString("var_table_name");
							String conditionString = rs1.getString("condition");
							// String conditionString=null;

							if (conditionString == null || conditionString.trim().equals("")) {
								conditionString = "";
							}

							if (column.trim().equals("trim(pension_appln_no)")) {
								content = applno;
							} else {
								qrystr = "select " + column + " from " + table + " " + conditionString;
								// System.out.println("sql-->\n" + qrystr);
								ps2 = con.prepareStatement(qrystr);
								if (qrystr.contains("?")) {
									if (st.equals("V67")) {
										ps2.setString(1, roleReceiver);
									}
									else {
										
										ps2.setString(1, applno);
									
									}
									if (st.equals("V23") || st.equals("V34") || st.equals("V38") || st.equals("V66")) {
										ps2.setString(2, applno);
									}
									
								}
								// System.out.println("sql--result>\n" + ps2.toString());
								rs2 = ps2.executeQuery();
								if (rs2.next()) {
									content = rs2.getString(1).trim();
								}
								System.out.println("sql--result>\n" + ps2.toString());
							}
						}
						msg += content + " ";
					}
					// P- Parameter;
					else if (st.startsWith("P")) {
						String cont = "";
						if (st.equals("P1")) {
							cont = dor;
						}
						if (st.equals("P2")) {
							cont = empid;
						}
						if (st.equals("P3")) {
							cont = otp;
						}
						msg += cont + " ";
					}
				}
			}
		} catch (SQLException ex) {
			System.out.println("Exception in SMSdao class createMsgAppl method " + ex);
			logger.info("Exception in SMSdao class createMsgAppl method-->" + ex);
		} finally {
			seqarr = null;
			ob1 = null;
			try {
				if (rs1 != null) {
					rs1.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
				if (ps2 != null) {
					ps2.close();
				}
				if (ps1 != null) {
					ps1.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
				logger.info("SQLException-->" + ex);
			}
		}
		// System.out.println("message body \n" + msg);
		logger.info("message body -->" + msg);
		return msg;
	}

}
