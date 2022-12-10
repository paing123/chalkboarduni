package edu.chalkboarduni.uniregistrationsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Login;
import edu.chalkboarduni.uniregistrationsystem.service.LoginService;
import edu.chalkboarduni.uniregistrationsystem.service.UserService;
import edu.chalkboarduni.uniregistrationsystem.util.DateTimeFormatter;

@Controller
@RequestMapping("admin")
public class UserController {

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
	
	@RequestMapping(value = {"users"})
	public String getUserList(UserDto user) {
		return "admin/users";
	}
	
	@RequestMapping(value = {"searchusers"})
	public String searchUserList(UserDto user) {
		List<UserDto> userList = userService.findUser(user);
		for (UserDto uzer : userList) {
			uzer.setDob(DateTimeFormatter.changeDateFormat(uzer.getDob()));
		}
		user.setUserList(userList);		
		return "admin/users";
	}
	
	@GetMapping(value = {"registeruser"})
	public String getUserForm(UserDto user) {
		user = new UserDto();
		return "admin/registeruser";
	}
	
	@PostMapping(value = {"registeruser"})
	public String registerUser(@Valid @ModelAttribute("model") UserDto user,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/registeruser";
		}
		
		try {
			userService.save(user);
			return "redirect:/admin/searchusers?successMessage=registered";
		} catch (Exception e) {
			user.setErrorMessage("Duplication Error! Please check User Id, Phone num, Email.");
			return "admin/registeruser";
		}
		
	}

	
	@GetMapping("updateuser/{id}")
	public String getExistUser(@PathVariable("id") Integer userId,Model model) {
		UserDto existUser = new UserDto();
		existUser.setUserId(userId);
		existUser = userService.findUser(existUser).get(0);
		model.addAttribute("model",existUser);
		return "admin/updateuser";
	}
	
	@PostMapping("updateuser")
	public String updateUser(@Valid @ModelAttribute("model") UserDto user,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/updateuser";
		}
		
		try {
			userService.update(user);
			return "redirect:/admin/searchusers?successMessage=updated";
		} catch (Exception e) {
			user.setErrorMessage("Duplication Error! Please check Phone num, Email.");
			return "admin/updateuser";
		}
	}
	
	@GetMapping("deleteuser/{id}")
	public String deleteUser(@PathVariable("id") String userId) {
		userService.delete(userId);
		return "redirect:/admin/searchusers?successMessage=deleted";
	}	
}
