package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.CampionatoPilotiService;

@Controller
public class CampionatoPilotiController {

    @Autowired
    private CampionatoPilotiService campionatoPilotiService;

    @GetMapping("/campionati/piloti")
    public String listaCampionatiPiloti(Model model) {
        model.addAttribute("campionatiPiloti", campionatoPilotiService.getAllCampionati());
        return "campionatiPiloti.html";
    }

    @GetMapping("/campionati/piloti/{id}")
    public String dettagliCampionatoPiloti(@PathVariable("id") Long id, Model model) {
        model.addAttribute("campionatoPiloti", campionatoPilotiService.getCampionatoById(id));
        return "dettaglioCampionatoPiloti.html";
    }
}
