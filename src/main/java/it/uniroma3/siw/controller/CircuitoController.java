package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Circuito;
import it.uniroma3.siw.service.CircuitoService;

@Controller
public class CircuitoController {
	@Autowired CircuitoService circuitoService;
	
	@GetMapping("/admin/formNewCircuito")
	public String formNewCircuito(Model model) {
		model.addAttribute("circuito", new Circuito());
		return "admin/formNewCircuito.html";
	}

}
