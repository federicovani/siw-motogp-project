package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Campionato;
import it.uniroma3.siw.model.CampionatoPiloti;
import it.uniroma3.siw.model.GranPremio;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.service.CampionatoPilotiService;
import it.uniroma3.siw.service.GranPremioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.service.CampionatoService;

import java.util.List;
import java.util.Map;

@Controller
public class CampionatoController {
	
	@Autowired
    CampionatoService campionatoService;
    @Autowired
    CampionatoPilotiService campionatoPilotiService;
    @Autowired
    GranPremioService granPremioService;
	
	@GetMapping("/campionati")
    public String showCampionati() {
        return "campionati.html";
    }

    @GetMapping("/campionatoPiloti")
    public String showCampionatoPiloti(@RequestParam(value = "anno", required = false) Integer anno, Model model) {
        if (anno == null)
            anno = campionatoService.getUltimoAnnoDisponibile();
        Campionato campionato = campionatoService.getCampionatoByAnno(anno);

        List<CampionatoPiloti> classifica = campionato.getClassifica();
        // Ordina la classifica per punti in ordine decrescente
        classifica.sort((cp1, cp2) -> Integer.compare(cp2.getPuntiTotali(), cp1.getPuntiTotali()));

        model.addAttribute("annoSelezionato", anno);
        model.addAttribute("anniDisponibili", campionatoService.getAnniDisponibili());
        model.addAttribute("campionato", campionato);
        model.addAttribute("classifica", classifica);

        return "campionatoPiloti.html";
    }

    @GetMapping("/campionatoTeam")
    public String showCampionatoTeam(@RequestParam(value = "anno", required = false) Integer anno, Model model) {
        if (anno == null)
            anno = campionatoService.getUltimoAnnoDisponibile();
        Campionato campionato = campionatoService.getCampionatoByAnno(anno);

        Map<Team, Integer> classifica = campionatoService.calcolaClassificaTeam(campionato);

        model.addAttribute("annoSelezionato", anno);
        model.addAttribute("anniDisponibili", campionatoService.getAnniDisponibili());
        model.addAttribute("campionato", campionato);
        model.addAttribute("classifica", classifica);

        return "campionatoTeam.html";
    }

    @GetMapping("/campionatoCostruttori")
    public String showCampionatoCostruttori(@RequestParam(value = "anno", required = false) Integer anno, Model model) {
        if (anno == null)
            anno = campionatoService.getUltimoAnnoDisponibile();
        Campionato campionato = campionatoService.getCampionatoByAnno(anno);

        Map<String, Integer> classifica = campionatoService.calcolaClassificaCostruttori(campionato);

        model.addAttribute("annoSelezionato", anno);
        model.addAttribute("anniDisponibili", campionatoService.getAnniDisponibili());
        model.addAttribute("campionato", campionato);
        model.addAttribute("classifica", classifica);

        return "campionatoCostruttori.html";
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

        List<GranPremio> granPremiDisponibili = granPremioService.getGranPremiByAnno(anno);
        model.addAttribute("granPremiDisponibili", granPremiDisponibili);
        model.addAttribute("annoSelezionato", anno);


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
            model.addAttribute("errorMessage", "Seleziona almeno un Gran Premio.");
            return "/admin/formNewCampionato.html";
        }
        // Crea e salva il nuovo campionato
        campionatoService.creaCampionato(anno, granPremi);
        return "redirect:/campionati";
    }

    @GetMapping("/admin/formUpdateCampionato/{id}")
    public String mostraFormUpdateCampionato(@PathVariable("id") Long id,
                                            Model model) {

        Campionato campionato = campionatoService.getCampionatoById(id);
        model.addAttribute("campionato", campionato);


        List<GranPremio> granPremiDisponibili = granPremioService.getGranPremiByAnno(campionato.getAnno());
        model.addAttribute("granPremiDisponibili", granPremiDisponibili);

        return "/admin/formUpdateCampionato.html";
    }

    @PostMapping("/admin/formUpdateCampionato/{id}")
    public String modificaCampionato(@PathVariable("id") Long id,
                                     @RequestParam List<Long> granPremi,
                                     Model model) {

        Campionato campionato = campionatoService.getCampionatoById(id);

        if (campionato == null) {
            model.addAttribute("errorMessage", "Campionato inesistente.");
            return "/campionati.html";
        }
        if (granPremi == null || granPremi.isEmpty()) {
            model.addAttribute("errorMessage", "Seleziona almeno un Gran Premio.");
            model.addAttribute("campionato", campionato);
            List<GranPremio> granPremiDisponibili = granPremioService.getGranPremiByAnno(campionato.getAnno());
            model.addAttribute("granPremiDisponibili", granPremiDisponibili);
            return "/admin/formUpdateCampionato.html";
        }
        // Modifica lista gran premi
        campionatoService.aggiornaCampionato(campionato, granPremi);
        return "redirect:/campionatoPiloti";
    }






}
