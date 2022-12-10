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

import edu.chalkboarduni.uniregistrationsystem.model.Hold;
import edu.chalkboarduni.uniregistrationsystem.service.HoldService;

@Controller
@RequestMapping("admin")
public class HoldController {

	@Autowired
	private HoldService holdService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(Hold hold, Model model) {
        model.addAttribute("model", hold);
    }
	
	@RequestMapping(value = {"holds"})
	public String getHoldList(Hold hold) {
		return "admin/holds";
	}
	
	@RequestMapping(value = {"searchholds"})
	public String searchHoldList(Hold hold) {
		List<Hold> holdList = holdService.findHold(hold);
		hold.setHoldList(holdList);
		return "admin/holds";
	}
	
	@GetMapping(value = {"registerhold"})
	public String getHoldForm(Hold hold) {
		hold = new Hold();
		return "admin/registerhold";
	}
	
	@PostMapping(value = {"registerhold"})
	public String registerHold(@Valid @ModelAttribute("model") Hold hold,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "admin/registerhold";
		}
		
		try {
			holdService.save(hold);
			return "redirect:/admin/searchholds?successMessage=registered";
		} catch (Exception e) {
			hold.setErrorMessage("Duplication Error! Please check HoldId.");
			return "admin/registerhold";
		}
	}
	
	@GetMapping("updatehold/{id}")
	public String getExistHold(@PathVariable("id") String holdId,Model model) {
		Hold existHold = new Hold();
		existHold.setHoldId(holdId);
		existHold = holdService.findHold(existHold).get(0);
		model.addAttribute("model",existHold);
		return "admin/updatehold";
	}
	
	@PostMapping("updatehold")
	public String updateHold(@Valid @ModelAttribute("model") Hold hold,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/updatehold";
		}
		holdService.update(hold);
		return "redirect:/admin/searchholds?successMessage=updated";
	}
	
	@GetMapping("deletehold/{id}")
	public String deleteHold(@PathVariable("id") String holdId) {
		holdService.delete(holdId);
		return "redirect:/admin/searchholds?successMessage=deleted";
	}
}
