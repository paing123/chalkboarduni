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

import edu.chalkboarduni.uniregistrationsystem.model.TimeSlot;
import edu.chalkboarduni.uniregistrationsystem.service.TimeSlotService;;

@Controller
@RequestMapping("admin")
public class TimeSlotController {

	@Autowired
	private TimeSlotService timeSlotService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ModelAttribute
    public void initModel(TimeSlot timeSlot, Model model) {
        model.addAttribute("model", timeSlot);
    }
	
	@RequestMapping(value = {"timeslots"})
	public String getTimeSlotList(TimeSlot timeSlot) {
		return "admin/timeslots";
	}
	
	@RequestMapping(value = {"searchtimeslots"})
	public String searchTimeSlotList(TimeSlot timeSlot) {
		List<TimeSlot> timeSlotList = timeSlotService.findTimeSlot(timeSlot);
		timeSlot.setTimeSlotList(timeSlotList);
		return "admin/timeslots";
	}
	
	@GetMapping(value = {"registertimeslot"})
	public String getTimeSlotForm(TimeSlot timeSlot) {
		timeSlot = new TimeSlot();
		return "admin/registertimeslot";
	}
	
	@PostMapping(value = {"registertimeslot"})
	public String registerTimeSlot(@Valid @ModelAttribute("model") TimeSlot timeSlot,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/registertimeslot";
		}
		timeSlotService.save(timeSlot);
		return "redirect:/admin/searchtimeslots?successMessage=registered";
	}
	
	@GetMapping("updatetimeslot/{id}")
	public String getExistTimeSlot(@PathVariable("id") Integer timeSlotId,Model model) {
		TimeSlot existTimeSlot = new TimeSlot();
		existTimeSlot.setTimeSlotId(timeSlotId);
		existTimeSlot = timeSlotService.findTimeSlot(existTimeSlot).get(0);
		model.addAttribute("model",existTimeSlot);
		return "admin/updatetimeslot";
	}
	
	@PostMapping("updatetimeslot")
	public String updateTimeSlot(@Valid @ModelAttribute("model") TimeSlot timeSlot,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/updatetimeslot";
		}
		timeSlotService.update(timeSlot);
		return "redirect:/admin/searchtimeslots?successMessage=updated";
	}
	
	@GetMapping("deletetimeslot/{id}")
	public String deleteTimeSlot(@PathVariable("id") Integer timeSlot) {
		timeSlotService.delete(timeSlot);
		return "redirect:/admin/searchtimeslots?successMessage=deleted";
	}
}
