package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Campionato;
import it.uniroma3.siw.model.CampionatoPiloti;
import it.uniroma3.siw.model.GranPremio;
import it.uniroma3.siw.service.CampionatoPilotiService;
import it.uniroma3.siw.service.GranPremioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.service.CampionatoService;

import java.util.List;

@Controller
public class CampionatoController {
	
	@Autowired
    CampionatoService campionatoService;
    @Autowired
    CampionatoPilotiService campionatoPilotiService;
    @Autowired
    GranPremioService granPremioService;

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
        List<CampionatoPiloti> classifica = campionato.getClassifica();

        // Ordina la classifica per punti in ordine decrescente
        classifica.sort((cp1, cp2) -> Integer.compare(cp2.getPuntiTotali(), cp1.getPuntiTotali()));

        model.addAttribute("campionato", campionato);
        model.addAttribute("classifica", classifica);
        return "campionatoPiloti.html";
    }

    @GetMapping("/admin/formNewCampionato")
    public String mostraFormNuovoCampionato(@RequestParam(value = "anno", required = false) Integer anno,
                                            Model model) {
        // Crea un nuovo oggetto Campionato che sarà legato al form
        Campionato nuovoCampionato = new Campionato();
        model.addAttribute("campionato", nuovoCampionato);

        // Recupera gli anni disponibili per la selezione
        List<Integer> anniDisponibili = granPremioService.getAnniDisponibili();
        model.addAttribute("anniDisponibili", anniDisponibili);
        //Per il primo caricamento seleziona in automatico l'ultimo anno disponibile
        if(anno==null)
            anno = granPremioService.getUltimoAnnoDisponibile();
        else {
            List<GranPremio> granPremiDisponibili = granPremioService.getGranPremiByAnno(anno);
            model.addAttribute("granPremiDisponibili", granPremiDisponibili);
            model.addAttribute("annoSelezionato", anno);
        }

        return "/admin/formNewCampionato.html";
    }


    @PostMapping("/admin/formNewCampionato")
    public String creaCampionato(@RequestParam int anno,
                                 @RequestParam List<Long> granPremi,
                                 Model model) {
        // Controlla se l'anno esiste già
        if (campionatoService.getCampionatoByAnno(anno) != null) {
            model.addAttribute("errorMessage", "Campionato per l'anno " + anno + " già esistente.");
            return "/admin/formNewCampionato.html";
        }
        if (granPremi.isEmpty()) {
            model.addAttribute("errorMessage", "Lista gran premi vuota");
            return "/admin/formNewCampionato.html";
        }
        // Crea e salva il nuovo campionato
        campionatoService.creaCampionato(anno, granPremi);
        return "redirect:/campionati";
    }






}
