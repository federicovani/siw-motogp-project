package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.GranPremioService;

@Controller
public class GranPremioController {
	
	@Autowired GranPremioService granPremioService;
	
	@GetMapping("/granPremi")
	public String showGranPremi(Model model) {
		model.addAttribute("granPremi", this.granPremioService.getAllGranPremi());
		return "granPremi.html";
	}
	
	@GetMapping("/granPremio/{id}")
	public String getGranPremio(@PathVariable("id") Long id, Model model) {
		model.addAttribute("granPremio", this.granPremioService.getGranPremioById(id));
		return "granPremio.html";
	}

}
