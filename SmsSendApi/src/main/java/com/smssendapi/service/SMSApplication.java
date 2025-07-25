package com.smssendapi.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smssendapi.dbconfig.DBconnection;


public class SMSApplication {
	Logger logger = LogManager.getLogger(SMSApplication.class);
	Properties properties = new Properties();
	InputStream inputStream;

	public String sendSms(String phNo, String msg, String TemplateId) {
		String result = "";
		SendSms sendsms = new SendSms();
		try {
			
			result = sendsms.sendSingleSMS(msg, phNo, TemplateId);
			/*inputStream = (InputStream) SMSApplication.class.getResourceAsStream("/emailSms.properties");
			properties.load(inputStream);

			switch (properties.getProperty("db.ip")) {
			case "10.176.67.80": // For Testing server
				// result = sendSmsT(phNo, msg);
				break;
			case "172.20.40.202": // For Training Server
				// result = sendSmsT(phNo, msg);
				break;
			case "192.168.1.10":
				// result = sendSmsT(phNo, msg);
				break;

			case "10.247.10.161":// For production server
				// result = sendSmsD(phNo, msg, TemplateId);
				break;
			case "172.25.135.130":// For production server DSC
				result = sendsms.sendSingleSMS(msg, phNo, TemplateId);
				break;
			case "noSms":// For Testing
				result = "no protocal";

				break;
			}*/
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Finally-->" + e);
		} 
		return result;
	}

	public int updateSmsStatus(String smsRes, String receiver_id, String sender_id, String send_to_mobile_no,
			String role, String sms) {
		logger.info("SMS Response Traker Action Start---->");
		String reqId = null;
		String rescode = null;
		String resInfo = null;
		String resTime = null;
		Date respdt = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		int n = 0;
		Connection con = null;
		;
		String respo = null;
		;

		String mening = null;
		String[] strarr = smsRes.split(",");
		try {
			con = DBconnection.OpenConnection();

			int respoccode = Integer.parseInt(strarr[0]);
			respo = strarr[0];
			reqId = strarr[1].substring(8);

			ps = con.prepareStatement("select error_description from sms_error_code_master where error_code=? ");
			ps.setInt(1, respoccode);

			rs = ps.executeQuery();
			while (rs.next()) {
				mening = rs.getString("error_description");
			}

			ps = con.prepareStatement("insert into sms_response_tracker(sender_id,send_to_mobile_no,role_id,sms_body,"
					+ "request_id,response_code,response_info,response_time,receiver_id)values(?,?,?,?,?,?,?,?,?)");

			ps.setString(1, sender_id);
			ps.setString(2, send_to_mobile_no);
			ps.setString(3, role);
			ps.setString(4, sms);
			ps.setString(5, reqId);
			ps.setString(6, respo);
			ps.setString(7, mening);
			ps.setTimestamp(8, new Timestamp(new Date().getTime()));
			ps.setString(9, receiver_id);
			System.out.println("insert into sms_response_tracker--->" + ps.toString());
			n = ps.executeUpdate();
			if (n > 0) {
				logger.info("::::: inserting data into sms_response_tracker Success:::::");
			} else {
				logger.info("::::: inserting data into sms_response_tracker Failed:::::");
			}

		} catch (Exception ex) {
			logger.info("SQLException on inserting data into sms_response_tracker---->" + ex);
			System.out.println("SQLException on inserting data into sms_response_tracker---->" + ex);
		} finally {
			try {
				if (n > 0) {
					con.commit();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				logger.info("ERROR---" + ex);
				logger.info("ERROR---->" + ex);
			}
		}
		return n;
	}
}
