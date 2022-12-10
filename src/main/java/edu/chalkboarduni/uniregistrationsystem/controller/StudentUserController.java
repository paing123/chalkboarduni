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
@RequestMapping("student")
public class StudentUserController {

	@Autowired
	private UserService userService;
		
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(UserDto user, Model model) {
        model.addAttribute("model", user);
    }
	
	@GetMapping("updatestudent")
	public String getExistUser(HttpSession session,Model model) {
		UserDto currentUser = (UserDto) session.getAttribute("currentUser");
		currentUser.setSuccessMessage(null);
		model.addAttribute("model",currentUser);
		return "student/updatestudent";
	}
	
	@PostMapping("updatestudent")
	public String updateUser(@Valid @ModelAttribute("model") UserDto user,HttpSession session,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "student/updatestudent";
		}
		
		userService.updateStudentOrFacultyUser(user);
		user.setSuccessMessage("updated");
		session.setAttribute("currentUser", user);
		return "student/updatestudent";
	}
}
