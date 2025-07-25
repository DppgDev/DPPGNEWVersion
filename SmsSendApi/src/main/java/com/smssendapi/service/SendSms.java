package com.smssendapi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.net.ssl.SSLContext;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
public class SendSms {
	Logger logger = LogManager.getLogger(SendSms.class);
	public String sendSingleSMS(String message,  String mobileNumber,String templateid
			) {
		InputStream inputStream;
		 String responseString = "";
        // SSLSocketFactory sf=null;
       //  SSLConnectionSocketFactory  sslsf =null;
         SSLContext context=null;
         String encryptedPassword;
		 Properties p = new Properties();
		 		try {
			
			 inputStream = (InputStream) SendSms.class.getResourceAsStream("/emailSms.properties");
	         p.load(inputStream);
	         String username= p.getProperty("USERNAME");
	         String password= p.getProperty("PASS");
	         String senderId= p.getProperty("ID");
	         String secureKey= p.getProperty("KEY");
	         String   https_url = p.getProperty("https_url");
			
	                
	         /*
	         
	         context = SSLContextBuilder.create()
	                    .setProtocol("TLSv1.2")
	                    .build();
	      
	         //context=SSLContext.getInstance("TLSv1.1"); // Use this line for Java version 6
	         context=SSLContext.getInstance("TLSv1.2"); // Use this line for Java version 7 and above
	         context.init(null, null, null);
	         sslsf=new SSLConnectionSocketFactory(context,SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	         Scheme scheme=new Scheme("https",443,sf);
	       //  HttpClient client=new DefaultHttpClient();
	         
	         CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
	         
	         
	         client.getConnectionManager().getSchemeRegistry().register(scheme);
	         HttpPost post=new HttpPost(https_url);
	         
	         */
	         
	      // Create SSLContext
	         SSLContext sslContext = SSLContexts.custom()
	                 .useProtocol("TLSv1.2")
	                 .build();

	         // Create socket factory (use NoopHostnameVerifier if you want to skip hostname checks)
	         SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                 sslContext,
	                 NoopHostnameVerifier.INSTANCE // OR SSLConnectionSocketFactory.getDefaultHostnameVerifier()
	         );

	         // Register https and http
	         Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
	                 .register("http", new PlainConnectionSocketFactory())
	                 .register("https", sslsf)
	                 .build();

	         // Create connection manager
	         PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

	         // Build HttpClient
	         CloseableHttpClient httpClient = HttpClients.custom()
	                 .setSSLSocketFactory(sslsf)
	                 .setConnectionManager(connManager)
	                 .build();

	         // Use HttpClient
	         HttpPost post = new HttpPost(https_url);
	         
	         
	         
	         
	         
	         encryptedPassword = MD5(password);
	         String genratedhashKey = hashGenerator(username, senderId, message, secureKey);
	         List nameValuePairs=new ArrayList(1);
	         nameValuePairs.add(new BasicNameValuePair("mobileno", mobileNumber));
	         nameValuePairs.add(new BasicNameValuePair("senderid", senderId));
	         nameValuePairs.add(new BasicNameValuePair("content", message));
	         nameValuePairs.add(new BasicNameValuePair("smsservicetype", "singlemsg"));
	         nameValuePairs.add(new BasicNameValuePair("username", username));
	         nameValuePairs.add(new BasicNameValuePair("password", encryptedPassword));
	         nameValuePairs.add(new BasicNameValuePair("key", genratedhashKey));
	         nameValuePairs.add(new BasicNameValuePair("templateid", templateid));
	         post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	         CloseableHttpResponse response = httpClient.execute(post);
	         //HttpResponse response=client.execute(post);
	         BufferedReader bf=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	         String line="";
	         while((line=bf.readLine())!=null){
	         responseString = responseString+line;
	         logger.info("Main ----->"+responseString);
	         }
	         if(responseString.equals("IP not Whitelisted"))
	         {
	        	  responseString="999,MsgID = 999999999"; 
	         }
	        
	         
				 
	         System.out.println(responseString);
	         logger.info("Sms Sent Response----->" + responseString);
	         } catch (NoSuchAlgorithmException e) {
	        	  logger.info("NoSuchAlgorithmException---->" + e);
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	         } catch (KeyManagementException e) {
	        	 logger.info("KeyManagementException---->" + e);
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	         } catch (UnsupportedEncodingException e) {
	        	 logger.info("UnsupportedEncodingException---->" + e);
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	         } catch (ClientProtocolException e) {
	        	 logger.info("ClientProtocolException---->" + e);
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	         } catch (IOException e) {
	        	 logger.info("IOException---->" + e);
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	         }
	         return responseString;
	         }

	private static String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] md5 = new byte[64];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		md5 = md.digest();
		return convertedToHex(md5);
	}

	private static String convertedToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfOfByte = (data[i] >>> 4) & 0x0F;
			int twoHalfBytes = 0;
			do {
				if ((0 <= halfOfByte) && (halfOfByte <= 9)) {
					buf.append((char) ('0' + halfOfByte));
				} else {
					buf.append((char) ('a' + (halfOfByte - 10)));
				}
				halfOfByte = data[i] & 0x0F;
			} while (twoHalfBytes++ < 1);
		}
		return buf.toString();
	}
	protected String hashGenerator(String userName, String senderId, String content, String secureKey) {
		// TODO Auto-generated method stub
		StringBuffer finalString = new StringBuffer();
		finalString.append(userName.trim()).append(senderId.trim()).append(content.trim()).append(secureKey.trim());
		// logger.info("Parameters for SHA-512 : "+finalString);
		String hashGen = finalString.toString();
		StringBuffer sb = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(hashGen.getBytes());
			byte byteData[] = md.digest();
			// convert the byte to hex format method 1
			sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			 logger.info("IOException---->" + e);
			e.printStackTrace();
		}
		return sb.toString();
	}
}
