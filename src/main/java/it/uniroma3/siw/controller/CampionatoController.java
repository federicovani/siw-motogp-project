package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Campionato;
import it.uniroma3.siw.model.CampionatoPiloti;
import it.uniroma3.siw.service.CampionatoPilotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.service.CampionatoService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CampionatoController {
	
	@Autowired
    CampionatoService campionatoService;
    @Autowired
    CampionatoPilotiService campionatoPilotiService;
	
	/*@GetMapping("/campionati")
	public String showCampionati(Model model) {
		model.addAttribute("campionati", this.campionatoService.getAllCampionati());
		ret*/
	
	@GetMapping("/campionati")
    public String showCampionati() {
        return "campionati.html";
    }

    @GetMapping("/campionatoPiloti/{anno}")
    public String classificaPiloti(@PathVariable("anno") int anno, Model model) {
        Campionato campionato = campionatoService.getCampionatoByAnno(anno);
        campionatoService.start(campionato);
        List<CampionatoPiloti> classifica = campionato.getClassifica();

        // Ordina la classifica per punti in ordine decrescente
        classifica.sort((cp1, cp2) -> Integer.compare(cp2.getPuntiTotali(), cp1.getPuntiTotali()));

        model.addAttribute("campionato", campionato);
        model.addAttribute("classifica", classifica);
        return "campionatoPiloti.html";
    }


}
