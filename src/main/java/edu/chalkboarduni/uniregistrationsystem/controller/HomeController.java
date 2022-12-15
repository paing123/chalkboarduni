package edu.chalkboarduni.uniregistrationsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.chalkboarduni.uniregistrationsystem.model.Hold;
import edu.chalkboarduni.uniregistrationsystem.model.Major;
import edu.chalkboarduni.uniregistrationsystem.service.MajorService;

@Controller
public class HomeController {

	@Autowired
	private MajorService majorService;

	@GetMapping(value = {"programs"})
	public String getMajorList(Model model) {
		List<Major> majors = majorService.findMajor(new Major());
		model.addAttribute("majors",majors);
		return "programs";
	}
	
	@GetMapping(value = {"academiccalendar"})
	public String getAcdemicCalendar() {
		return "academiccalendar";
	}
}
