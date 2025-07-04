package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.GranPremioService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GranPremioController {
	
	@Autowired GranPremioService granPremioService;

	@GetMapping("/granPremi")
	public String showGranPremi(@RequestParam(value = "anno", required = false) Integer anno, Model model) {
		if (anno != null) {
			model.addAttribute("granPremi", granPremioService.getGranPremiByAnno(anno));
		} else {
			model.addAttribute("granPremi", granPremioService.getGranPremiByAnno(granPremioService.getUltimoAnnoDisponibile()));
		}

		// Aggiungi anche gli anni unici disponibili per il selettore
		model.addAttribute("anniDisponibili", granPremioService.getAnniDisponibili());

		return "granPremi.html";
	}


	@GetMapping("/granPremio/{id}")
	public String getGranPremio(@PathVariable("id") Long id, Model model) {
		model.addAttribute("granPremio", this.granPremioService.getGranPremioById(id));
		return "granPremio.html";
	}

}
