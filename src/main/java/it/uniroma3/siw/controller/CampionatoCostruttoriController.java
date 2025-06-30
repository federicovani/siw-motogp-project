package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.CampionatoCostruttoriService;

@Controller
public class CampionatoCostruttoriController {

    @Autowired
    private CampionatoCostruttoriService campionatoCostruttoriService;

    @GetMapping("/campionati/costruttori")
    public String listaCampionatiCostruttori(Model model) {
        model.addAttribute("campionatiCostruttori", campionatoCostruttoriService.getAllCampionati());
        return "campionatiCostruttori.html";
    }

    @GetMapping("/campionati/costruttori/{id}")
    public String dettagliCampionatoCostruttori(@PathVariable("id") Long id, Model model) {
        model.addAttribute("campionatoCostruttori", campionatoCostruttoriService.getCampionatoById(id));
        return "dettaglioCampionatoCostruttori.html";
    }
}
