package com.smssendapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smssendapi.model.SMSActivityRelationship;



public class SmsAndMailDao {
	 Logger logger = LogManager.getLogger(SmsAndMailDao.class.getName());
	 public static List<SMSActivityRelationship> getSMSActivityList(int activityCode, String dept_cd, String sub_dept_cd,
				Connection con)
	 {
		 SMSActivityRelationship smsar1 = null;
			List<SMSActivityRelationship> smsarList = new ArrayList<SMSActivityRelationship>();

			ResultSet rs = null;
			PreparedStatement ps = null;
			try {
				smsar1 = new SMSActivityRelationship();
				
				if(activityCode==98)
				{
					ps = con.prepareStatement(
							"select distinct sms_content_id,sender_role_id,receiver_role_id,dept_cd,sub_dept_cd from sms_activity_role_relationship_master \n"
									+ "where activity_code=? and dept_cd=? and sub_dept_cd=? order by sms_content_id asc");
					ps.setInt(1, activityCode);
					ps.setString(2, "99");
					ps.setString(3, "01");
					
				}
				else
				{
					ps = con.prepareStatement(
							"select distinct sms_content_id,sender_role_id,receiver_role_id,dept_cd,sub_dept_cd from sms_activity_role_relationship_master \n"
									+ "where activity_code=? and dept_cd=? and sub_dept_cd=? order by sms_content_id asc");
					ps.setInt(1, activityCode);
					ps.setString(2, dept_cd);
					ps.setString(3, sub_dept_cd);
				}
				
				/*ps = con.prepareStatement(
						"select distinct sms_content_id,sender_role_id,receiver_role_id,dept_cd,sub_dept_cd from sms_activity_role_relationship_master \n"
								+ "where activity_code=? and dept_cd=? and sub_dept_cd=? order by sms_content_id asc");
				ps.setInt(1, activityCode);
				ps.setString(2, dept_cd);
				ps.setString(3, sub_dept_cd);*/
				rs = ps.executeQuery();
				System.out.println("sms Relatiom Query---" + ps.toString());
				while (rs.next()) {
					smsar1 = new SMSActivityRelationship();
					smsar1.setSmsContentId(rs.getString("sms_content_id"));
					smsar1.setReceiverRoleId(rs.getString("receiver_role_id"));
					smsar1.setSenderRoleId(rs.getString("sender_role_id"));
					smsar1.setDept_cd(rs.getString("dept_cd"));
					smsar1.setSub_dept_cd(rs.getString("sub_dept_cd"));
					smsarList.add(smsar1);
				}
			} catch (SQLException e) {
				System.out.println("ERROR---" + e);
				
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}

				} catch (SQLException w) {
					System.out.println("Exception in timerSMS finally " + w);
					
				}
			}
			
			
			return smsarList;
	 }
}
