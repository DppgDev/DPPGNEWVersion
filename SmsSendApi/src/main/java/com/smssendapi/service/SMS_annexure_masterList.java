package com.smssendapi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smssendapi.dbconfig.DBconnection;



public class SMS_annexure_masterList {
	Logger logger = LogManager.getLogger(SMS_annexure_masterList.class);
	 public SMS_annexure_masterList(){
	    	
	    }
	    
	    //List<SMS_annexure_master> amlist = new ArrayList<SMS_annexure_master>();
	    private String getlist(String sms_id) {
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	         String seq=null;
	         String tempid=null;
	         try {
	        	 con = DBconnection.OpenConnection();
	            ps = con.prepareStatement("select sequence from sms_annexure_master where TRIM(sms_annexure_id)=?");
	            ps.setString(1, sms_id.trim());
	            rs = ps.executeQuery();
	            if (rs.next()) {
	               
	                seq=rs.getString("sequence");
	               
	            }
	           
	            con.close();

	        } catch (SQLException e) {
	            System.out.println("exception---" + e);
	            logger.info("Error-->" + e);
	        } finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                 if (ps != null) {
	                    ps.close();
	                }
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException ex) {
	            	 System.out.println("Finally---" + ex);
	            	 logger.info("Error-->" + ex);
	            }
	        }
	        //return amlist;
	         //System.out.println("My sequence list is " + seq);
	        return seq;
	    }
	    public String compareAnnexure(String sms_annexure_id) {
	        String seq=null;
	        seq=getlist(sms_annexure_id);
	        //System.out.println("SMS ANEXTURE ID-->>"+sms_annexure_id);
	        //System.out.println("ANEXTURELIST-->>"+seq);
	        return seq;
	    }

		public String getTemplateId(String sms_id) {
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	         String seq=null;
	         String tempid=null;
	       
	         try {
	        	 con = DBconnection.OpenConnection();
	            ps = con.prepareStatement("select template_id from sms_annexure_master where TRIM(sms_annexure_id)=?");
	            ps.setString(1, sms_id.trim());
	            rs = ps.executeQuery();
	            if (rs.next()) {
	               
	               
	                tempid=rs.getString("template_id");
	            }
	           
	            con.close();

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
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException ex) {
	            	 System.out.println("Finally---" + ex);
	            }
	        }
	        //return amlist;
	         //System.out.println("My sequence list is " + seq);
	        return tempid;
	    }
}