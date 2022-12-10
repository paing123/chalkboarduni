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
import edu.chalkboarduni.uniregistrationsystem.service.BuildingService;

@Controller
@RequestMapping("admin")
public class BuildingController {

	@Autowired
	private BuildingService buildingService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Building building, Model model) {
        model.addAttribute("model", building);
    }
	
	@RequestMapping(value = {"buildings"})
	public String getBuildingList(Building building) {
		return "admin/buildings";
	}
	
	@RequestMapping(value = {"searchbuildings"})
	public String searchBuildingList(Building building) {
		List<Building> buildingList = buildingService.findBuilding(building);
		building.setBuildingList(buildingList);
		return "admin/buildings";
	}
	
	@GetMapping(value = {"registerbuilding"})
	public String getBuildingForm(Building building) {
		building = new Building();
		return "admin/registerbuilding";
	}
	
	@PostMapping(value = {"registerbuilding"})
	public String registerBuilding(@Valid @ModelAttribute("model") Building building, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/registerbuilding";
		}
		
		try {
			buildingService.save(building);
			return "redirect:/admin/searchbuildings?successMessage=registered";
		} catch (Exception e) {
			building.setErrorMessage("Duplication Error! Please check Building ID");
			return "admin/registerbuilding";
		}
		
		
	}
	
	@GetMapping("updatebuilding/{id}")
	public String getExistBuilding(@PathVariable("id") String buildingId,Model model) {
		Building existBuilding = new Building();
		existBuilding.setBuildingId(buildingId);
		existBuilding = buildingService.findBuilding(existBuilding).get(0);
		model.addAttribute("model",existBuilding);
		return "admin/updatebuilding";
	}
	
	@PostMapping("updatebuilding")
	public String updateBuilding(@Valid @ModelAttribute("model") Building building, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "admin/updatebuilding";
		}
		
		buildingService.update(building);
		return "redirect:/admin/searchbuildings?successMessage=updated";
		
	}
	
	@GetMapping("deletebuilding/{id}")
	public String deleteBuilding(@PathVariable("id") String buildingId) {
		buildingService.delete(buildingId);
		return "redirect:/admin/searchbuildings?successMessage=deleted";
	}
}
