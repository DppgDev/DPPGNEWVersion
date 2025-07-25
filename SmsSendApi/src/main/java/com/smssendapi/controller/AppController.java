package com.smssendapi.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smssendapi.service.SMSApplication;
import com.smssendapi.service.SMSServices;

import jakarta.servlet.http.HttpServletRequest;




@RestController
@RequestMapping(value = "/")
public class AppController {
		 Logger logger = LogManager.getLogger(AppController.class.getName());
	
		 @GetMapping("/")
		    public ModelAndView index(Model model) {
		        
			 ModelAndView ob = new ModelAndView("index");
				return ob;  // returns hello.html from templates
		    }
		 
		 @PostMapping( "/sendsmsapi")
		 public ModelAndView SendSms(@RequestParam("mob") String mob,HttpServletRequest request)
		 {
			 ModelAndView mav = new ModelAndView("index");  
			 SMSApplication send=new SMSApplication();
				String msg="Your One Time Password(OTP) is  815811 . Do not share it with others person. WB E-Pension.WBIFMS";
				//String mob="8101035253";
				String templateid="1307173891346976082";
				logger.info(templateid);
				System.out.println(templateid);
				String response=send.sendSms(mob,msg,templateid);
			
				mav.addObject("response", response);
			return mav;
			 
		 }
		 
}
