package edu.chalkboarduni.uniregistrationsystem.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Login;
import edu.chalkboarduni.uniregistrationsystem.service.LoginService;
import edu.chalkboarduni.uniregistrationsystem.service.UserService;

@Controller
public class LoginUserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
		
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(UserDto user,Model model) {		
        model.addAttribute("model", user);
    }

	@GetMapping("/student/index")
	public String getStudentIndex(UserDto user, HttpSession session) {
		setCurrentUserToSession(user,session);
		return "redirect:/student/studenthome";
	}
	
	@RequestMapping({"/student/studenthome","/student/"})
	public String getStudentHome(UserDto user, HttpSession session) {
		return "student/studenthome";
	}
	
	@GetMapping("/faculty/index")
	public String getFacultyIndex(UserDto user, HttpSession session) {
		setCurrentUserToSession(user,session);
		return "redirect:/faculty/facultyhome";
	}
	
	@RequestMapping({"/faculty/facultyhome","/faculty/"})
	public String getFacultyHome(UserDto user, HttpSession session) {
		return "faculty/facultyhome";
	}
	
	@GetMapping("/admin/index")
	public String getAdminIndex(UserDto user, HttpSession session) {
		setCurrentUserToSession(user,session);
		return "redirect:/admin/adminhome";
	}
	
	@RequestMapping({"/admin/adminhome","/admin/"})
	public String getAdminHome(UserDto user, HttpSession session) {
		return "admin/adminhome";
	}
	
	@GetMapping("/researcherstaff/index")
	public String getResearcherStaffIndex(UserDto user, HttpSession session) {
		setCurrentUserToSession(user,session);
		return "redirect:/researcherstaff/researcherstaffhome";
	}
	
	@RequestMapping({"/researcherstaff/researcherstaffhome","/researcherstaff/"})
	public String getResearcherStaffHome(UserDto user, HttpSession session) {
		return "researcherstaff/researcherstaffhome";
	}
	
	private void setCurrentUserToSession(UserDto user, HttpSession session) {
		if(user.getUserId() == null) {
			String loginEmail = session.getAttribute("currentUser").toString();
			Login login = new Login();
			login.setEmail(loginEmail);
			login = loginService.findLogin(login).get(0);
			UserDto currentUser = new UserDto();
			currentUser.setUserId(login.getUserId());
	        currentUser.setEmail(loginEmail);
	        currentUser = userService.findUser(currentUser).get(0);
	        session.setAttribute("currentUser", currentUser);
		}
		
	}
}
