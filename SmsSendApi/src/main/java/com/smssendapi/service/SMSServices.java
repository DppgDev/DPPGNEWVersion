package com.smssendapi.service;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.smssendapi.dao.SmsAndMailDao;
import com.smssendapi.dbconfig.DBconnection;
import com.smssendapi.model.SMSActivityRelationship;
import com.smssendapi.model.SMSMailActivityMaster;
import com.smssendapi.model.UserMaster;



public class SMSServices {
	Connection con = null;
    UserMaster userDetails = null;
    List<SMSMailActivityMaster> activityobjList = null;
    String returnMsg = "", msgContent = null;
    List<SMSActivityRelationship> smsarList = null;
	Iterator<SMSActivityRelationship> iit = null;
	SMSActivityRelationship smsar = null;
	Logger logger = LogManager.getLogger(SMSServices.class);
	public String sendSingleSMS(String bodyData) throws Exception
	{
		logger.info(":::::::Single SMS SENding START ::::::::");
		String mobNo = null;
		String mailId = null;
		String smsContent = null;
		String sendmsg = null;
		String result = null;
		String templateid=null;
		 int n=0;
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(bodyData);
		String UserIdR = (String) jsonObject.get("UserIdR");
		String UserIdS = (String) jsonObject.get("UserIdS");
		String roleReceiver = (String) jsonObject.get("reciverroleid");
		String roleSender = (String) jsonObject.get("senderroleid");
		String applno = (String) jsonObject.get("penAppNo");
		String emp_id =(String) jsonObject.get("empid");
		String deptcode =(String) jsonObject.get("Deptcode");
		String subdeptcode =(String) jsonObject.get("SubDeptcode");
		String acticode=(String) jsonObject.get("activityCode");
		String password=(String) jsonObject.get("password");
		String dor=(String) jsonObject.get("dor");
		int activecode = Integer.parseInt(acticode.trim());
		String smsfrom=(String) jsonObject.get("smsfrom");
		String Freshrevision =(String) jsonObject.get("Freshrevision");
		logger.info(":::::::JSON DATA FATCH DONE ::::::::");
		SMS_annexure_masterList ob1 = new SMS_annexure_masterList(); 
		MailSmsService mailsmsservice=new MailSmsService();
		SMSApplication send=new SMSApplication();
		
		try {
			con  =DBconnection.OpenConnection();
			smsarList = SmsAndMailDao.getSMSActivityList(activecode,deptcode.trim(),subdeptcode.trim(),con);
			logger.info(":::::::smsarList-->" + smsarList.toString());
			iit=smsarList.iterator();
			while (iit.hasNext())
			{
				smsar= iit.next();
				if(smsfrom.equals("S"))
				{
					userDetails = mailsmsservice.getPhoneNoEmail(emp_id, "participating_dept_emp_info", con);
					mobNo = userDetails.getMobile_no();
					mailId = userDetails.getE_mail_id();
					smsContent = smsar.getSmsContentId();
					 templateid = mailsmsservice.getTemplateId(smsContent,con);
					 sendmsg = mailsmsservice.createMsgAppl(smsContent,roleReceiver, applno,dor,emp_id,password,con);
					 result = send.sendSms(mobNo, sendmsg,templateid);
					  n=send.updateSmsStatus(result, UserIdR, UserIdS, mobNo, roleSender, sendmsg);
					 System.out.println("sendmsg----Result--->>" + result+"With ----Mob---"+mobNo);
					 logger.info("sendmsg----Result--->>" + result+"With ----Mob---"+mobNo);
				} else if(smsfrom.equals("OTP"))
				{
					String mobile=(String) jsonObject.get("mobile");
					smsContent = smsar.getSmsContentId();
					templateid = mailsmsservice.getTemplateId(smsContent,con);
					sendmsg = mailsmsservice.createMsgAppl(smsContent,roleReceiver, applno,dor,emp_id,password,con);
					 result = send.sendSms(mobile, sendmsg,templateid);
					  n=send.updateSmsStatus(result, UserIdR, UserIdS, mobile, roleSender, sendmsg);
					 System.out.println("sendmsg----Result--->>" + result+"With ----Mob---"+mobile+"--Responser Taker-----status--"+n);
					 logger.info("sendmsg----Result--->>" + result+"With ----Mob---"+mobNo);
				}
				 else if(smsfrom.equals("CREAT"))
					{
						String mobile=(String) jsonObject.get("mobile");
						smsContent = smsar.getSmsContentId();
						templateid = mailsmsservice.getTemplateId(smsContent,con);
						sendmsg = mailsmsservice.createMsgAppl(smsContent,roleReceiver, applno,dor,emp_id,password,con);
						 result = send.sendSms(mobile, sendmsg,templateid);
						  n=send.updateSmsStatus(result, UserIdR, UserIdS, mobile, roleSender, sendmsg);
						 System.out.println("sendmsg----Result--->>" + result+"With ----Mob---"+mobile+"--Responser Taker-----status--"+n);
						 logger.info("sendmsg----Result--->>" + result+"With ----Mob---"+mobNo);
					}
				else
				{
					if (smsar.getReceiverRoleId().trim().equals(roleReceiver.trim())
							|| smsar.getReceiverRoleId().trim().equals("15")) 
					{
						if (smsar.getReceiverRoleId().trim().equals("15"))
						{
							userDetails =  mailsmsservice.getPhoneNoEmail(emp_id, "emp_mast", con);
							mobNo = userDetails.getMobile_no();
							mailId = userDetails.getE_mail_id();
							smsContent = smsar.getSmsContentId();
							 templateid = ob1.getTemplateId(smsContent);
							sendmsg = mailsmsservice.createMsgAppl(smsContent,roleReceiver, applno,dor,emp_id,password,con);
							
							 System.out.println("sendmsg>>\n" + sendmsg);
							 
							 result = send.sendSms(mobNo, sendmsg,templateid);
							  n=send.updateSmsStatus(result, UserIdR, UserIdS, mobNo,roleReceiver, sendmsg);
							 System.out.println("sendmsg----Result--->>" + result+"With ----Mob---"+mobNo);
							 logger.info("sendmsg----Result--->>" + result+"With ----Mob---"+mobNo);
								}
						
						else
						{
							userDetails =  mailsmsservice.getPhoneNoEmail(UserIdR, "user_master", con);
							mobNo = userDetails.getMobile_no();
							mailId = userDetails.getE_mail_id();
							smsContent = smsar.getSmsContentId();
							sendmsg = mailsmsservice.createMsgAppl(smsContent,roleReceiver, applno,dor,emp_id,password,con);
							 System.out.println("sendmsg>>\n" + sendmsg);
							 templateid = ob1.getTemplateId(smsContent);
							 result = send.sendSms(mobNo, sendmsg,templateid);
							  n=send.updateSmsStatus(result, UserIdR, UserIdS, mobNo,roleReceiver, sendmsg);
							System.out.println("sendmsg----Result--->>" + result+"With ----Mob---"+mobNo);
							 logger.info("sendmsg----Result--->>" + result+"With ----Mob---"+mobNo);
							// System.out.println("smsContent>>\n" + smsContent);
							
							
						}
						
						//  URL reconstructedURL = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), dummyServerPath);	
						
						
					}
				}
			}
		}
		catch (Exception ex) {
			// TODO: handle exception
			System.out.println(":::::sendSingleSMS:::::"+ex);
			logger.info(":::::::sendSingleSMS ::::Exception::::"+ex);
		}
		finally {
			try {
				if (con != null) {
					con.close();
				}
				

			} catch (SQLException w) {
				System.out.println("Exception in timerSMS finally " + w);
				
			}
		}
		return result+","+n;
		
	}
	
}
