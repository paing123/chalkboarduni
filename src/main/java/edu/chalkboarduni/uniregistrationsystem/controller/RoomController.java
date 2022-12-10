package edu.chalkboarduni.uniregistrationsystem.controller;

import java.util.List;

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

import edu.chalkboarduni.uniregistrationsystem.model.Building;
import edu.chalkboarduni.uniregistrationsystem.model.Room;
import edu.chalkboarduni.uniregistrationsystem.service.BuildingService;
import edu.chalkboarduni.uniregistrationsystem.service.RoomService;

@Controller
@RequestMapping("admin")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private BuildingService buildingService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Room room, Model model) {
        model.addAttribute("model", room);
    }
	
	@RequestMapping(value = {"rooms"})
	public String getRoomList(Room room) {
		List<Building> buildingList = buildingService.findBuilding(new Building());
		room.setBuildingList(buildingList);
		return "admin/rooms";
	}
	
	@RequestMapping(value = {"searchrooms"})
	public String searchRoomList(Room room) {
		List<Room> roomList = roomService.findRoom(room);
		List<Building> buildingList = buildingService.findBuilding(new Building());
		room.setRoomList(roomList);
		room.setBuildingList(buildingList);
		return "admin/rooms";
	}
	
	@GetMapping(value = {"registerroom"})
	public String getRoomForm(Room room,Model model) {
		room = new Room();
		List<Building> buildingList = buildingService.findBuilding(new Building());
		room.setBuildingList(buildingList);
		model.addAttribute("model",room);
		return "admin/registerroom";
	}
	
	@PostMapping(value = {"registerroom"})
	public String registerRoom(@Valid @ModelAttribute("model") Room room, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Building> buildingList = buildingService.findBuilding(new Building());
			room.setBuildingList(buildingList);
			return "admin/registerroom";
		}
		
		try {
			roomService.save(room);
			return "redirect:/admin/searchrooms?successMessage=registered";
		} catch (Exception e) {
			List<Building> buildingList = buildingService.findBuilding(new Building());
			room.setBuildingList(buildingList);
			room.setErrorMessage("Duplication Error! Please check RoomId.");
			return "admin/registerroom";
		}
		
	}
	
	@GetMapping("updateroom/{id}")
	public String getExistRoom(@PathVariable("id") String roomId,Model model) {
		Room existRoom = new Room();
		List<Building> buildingList = buildingService.findBuilding(new Building());
		existRoom.setRoomId(roomId);
		existRoom = roomService.findRoom(existRoom).get(0);
		existRoom.setBuildingList(buildingList);
		model.addAttribute("model",existRoom);
		return "admin/updateroom";
	}
	
	@PostMapping("updateroom")
	public String updateRoom(@Valid @ModelAttribute("model") Room room, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<Building> buildingList = buildingService.findBuilding(new Building());
			room.setBuildingList(buildingList);
			return "admin/updateroom";
		}
		roomService.update(room);
		return "redirect:/admin/searchrooms?successMessage=updated";
	}
	
	@GetMapping("deleteroom/{id}")
	public String deleteRoom(@PathVariable("id") String roomId) {
		roomService.delete(roomId);
		return "redirect:/admin/searchrooms?successMessage=deleted";
	}
}
