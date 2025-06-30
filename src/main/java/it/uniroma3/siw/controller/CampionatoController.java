package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.service.CampionatoService;

@Controller
public class CampionatoController {
	
	@Autowired CampionatoService campionatoService;
	
	/*@GetMapping("/campionati")
	public String showCampionati(Model model) {
		model.addAttribute("campionati", this.campionatoService.getAllCampionati());
		ret*/
	
	@GetMapping("/campionati")
    public String showCampionati() {
        return "campionati.html";
    }

}
