package com.example.demo.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import com.example.demo.dao.PatientRepository;
import com.example.demo.entities.Patient;

import org.springframework.data.domain.Page;
@Controller
public class PatientController {
	@Autowired
	private PatientRepository patientRep;
	@GetMapping(path="/patients")
	
	public String index(Model model,@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="4")int size) {
		Page<Patient> pagePatients = (Page) patientRep.findAll(PageRequest.of(page, size));
		model.addAttribute("patients",pagePatients.getContent());
		model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage",pagePatients.getNumber());
	//	System.out.println("pages: "+ (new int[pagePatients.getTotalPages()]).length);
		return "patients";
	}
}
