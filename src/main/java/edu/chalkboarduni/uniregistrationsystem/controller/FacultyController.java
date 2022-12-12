package edu.chalkboarduni.uniregistrationsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.cache.annotation.EnableCaching;
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

import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Department;
import edu.chalkboarduni.uniregistrationsystem.model.Faculty;
import edu.chalkboarduni.uniregistrationsystem.model.Room;
import edu.chalkboarduni.uniregistrationsystem.service.FacultyService;
import edu.chalkboarduni.uniregistrationsystem.service.RoomService;

@Controller
@RequestMapping("admin")
public class FacultyController {

	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private RoomService roomService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Faculty faculty, Model model) {
        model.addAttribute("model", faculty);
    }
	
	@RequestMapping(value = {"faculties"})
	public String getFacultyList(Faculty faculty) {
		List<Room> roomList = roomService.findRoom(new Room());
		List<UserDto> userList = facultyService.getFacultyUserList();
		faculty.setRoomList(roomList);
		faculty.setFacultyUserList(userList);
		return "admin/faculties";
	}
	
	@RequestMapping(value = {"searchfaculties"})
	public String searchFacultyList(Faculty faculty) {
		List<Faculty> facultyList = facultyService.findFaculty(faculty);
		List<Room> roomList = roomService.findRoom(new Room());
		List<UserDto> userList = facultyService.getFacultyUserList();
		faculty.setFacultyList(facultyList);
		faculty.setRoomList(roomList);
		faculty.setFacultyUserList(userList);
		return "admin/faculties";
	}
	
	@GetMapping(value = {"registerfaculty"})
	public String getFacultyForm(Faculty faculty,Model model) {
		faculty = new Faculty();
		List<Room> roomList = roomService.findRoom(new Room());
		List<UserDto> userList = facultyService.getFacultyUserList();
		faculty.setRoomList(roomList);
		faculty.setFacultyUserList(userList);
		model.addAttribute("model",faculty);
		return "admin/registerfaculty";
	}
	
	@PostMapping(value = {"registerfaculty"})
	public String registerFaculty(@Valid @ModelAttribute("model") Faculty faculty, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Room> roomList = roomService.findRoom(new Room());
			List<UserDto> userList = facultyService.getFacultyUserList();
			faculty.setRoomList(roomList);
			faculty.setFacultyUserList(userList);
			return "admin/registerfaculty";
		}
		
		try {
			facultyService.save(faculty);
			return "redirect:/admin/searchfaculties?successMessage=registered";
		} catch (Exception e) {
			List<Room> roomList = roomService.findRoom(new Room());
			List<UserDto> userList = facultyService.getFacultyUserList();
			faculty.setRoomList(roomList);
			faculty.setFacultyUserList(userList);
			faculty.setErrorMessage("Duplication Error! Please check FacultyId.");
			return "admin/registerfaculty";
		}
	}
	
	@GetMapping("updatefaculty/{id}")
	public String getExistFaculty(@PathVariable("id") Integer facultyId,Model model) {
		Faculty existFaculty = new Faculty();
		List<Room> roomList = roomService.findRoom(new Room());
		List<UserDto> userList = facultyService.getFacultyUserList();
		existFaculty.setFacultyId(facultyId);
		existFaculty = facultyService.findFaculty(existFaculty).get(0);
		existFaculty.setRoomList(roomList);
		existFaculty.setFacultyUserList(userList);
		model.addAttribute("model",existFaculty);
		return "admin/updatefaculty";
	}
	
	@PostMapping("updatefaculty")
	public String updateFaculty(@Valid @ModelAttribute("model") Faculty faculty, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Room> roomList = roomService.findRoom(new Room());
			List<UserDto> userList = facultyService.getFacultyUserList();
			faculty.setRoomList(roomList);
			faculty.setFacultyUserList(userList);
			return "admin/updatefaculty";
		}
		
		facultyService.update(faculty);
		return "redirect:/admin/searchfaculties?successMessage=updated";
	}
	
	@GetMapping("deletefaculty/{id}")
	public String deleteFaculty(@PathVariable("id") String facultyId) {
		facultyService.delete(facultyId);
		return "redirect:/admin/searchfaculties?successMessage=deleted";
	}
}
