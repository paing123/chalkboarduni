package edu.chalkboarduni.uniregistrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;

@Controller
public class LoginController {
	
	@RequestMapping({"/","index"})
	public String getIndexPage(UserDto user) {
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("model", new UserDto());
		return "login";
	}
}
