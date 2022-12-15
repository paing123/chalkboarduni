package edu.chalkboarduni.uniregistrationsystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Advising;
import edu.chalkboarduni.uniregistrationsystem.model.Login;
import edu.chalkboarduni.uniregistrationsystem.service.LoginService;
import edu.chalkboarduni.uniregistrationsystem.service.MyEmailService;
import edu.chalkboarduni.uniregistrationsystem.service.OtpService;
import edu.chalkboarduni.uniregistrationsystem.service.UserService;

@Controller
public class OtpController {
		
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private SpringTemplateEngine template;
	
	@Autowired
	private MyEmailService myEmailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@ModelAttribute
    public void initModel(UserDto user, Model model) {
        model.addAttribute("model", user);
    }
	
	@GetMapping("/forgetpassword")
	public String getForgetPasswordPage(UserDto user) {
		return "forgetpassword";
	}

	@PostMapping("/generateotp")
	public String generateOtp(UserDto user) throws Exception{
		
		int otp = otpService.generateOTP(user.getEmail());
		
		//Generate The Template to send OTP 
		//EmailTemplate template = new EmailTemplate("SendOtp.html");
		
		Map<String,Object> replacements = new HashMap<String,Object>();
		replacements.put("user", user.getEmail());
		replacements.put("otpnum", String.valueOf(otp));
		Context context = new Context();
		context.setVariables(replacements);
		 
		//String message = template.getTemplate(replacements);
		//templateEngine.process(templateFileName, new Context(Locale.getDefault(), variables));
		String message = template.process("SendOtp",context);
		myEmailService.sendOtpMessage(user.getEmail(), "OTP for password reset", message);
		return "checkotp";
	}
	
	@PostMapping("/validateotp")
	public String validateOtp(UserDto user){
		
		int otpnum = user.getOtp();
		String username = user.getEmail();
		
		//Validate the Otp 
		int serverOtp = otpService.getOtp(username);
		
		if(otpnum == serverOtp){
			
			otpService.clearOTP(username);
			
			Login login = new Login();
			login.setEmail(username);
			
			int checkUser = loginService.findLogin(login).size();
			
			if(checkUser == 0) {
				user.setErrorMessage("User don't exist");
				return "checkotp";
			}
			
			return "changepassword";
		}else{
			user.setErrorMessage("Invalid OTP, try again.");
			return "checkotp";	
		}
	}
	
	@PostMapping("/changepassword")
	public String changePassword(UserDto user, Model model){
		
		String email = user.getEmail();
		
		Login login = new Login();
		login.setEmail(email);
		login = loginService.findLogin(login).get(0);
		
		UserDto updUser = new UserDto();
		updUser.setUserId(login.getUserId());
		updUser = userService.findUser(updUser).get(0);
		updUser.setPassword(user.getPassword());
		userService.update(updUser);
		model.addAttribute("resetpassword","resetpassword");
		return "login";
	}
}
