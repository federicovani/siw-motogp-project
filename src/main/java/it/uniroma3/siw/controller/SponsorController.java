package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.Sponsor;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SponsorController {

   @Autowired
    SponsorService sponsorService;

    @GetMapping("/admin/formNewSponsor")
    public String formNewSponsor(Model model) {
        model.addAttribute("sponsor", new Sponsor());
        return "admin/formNewSponsor.html";
    }

    @PostMapping("/admin/formNewSponsor")
    public String salvaSponsor(@ModelAttribute("team") Sponsor sponsor,
                            @RequestParam("file") MultipartFile file,
                            Model model) {

        if (!sponsorService.existsByNome(sponsor.getNome())) {
            //Gestisci immagine
            if (file != null && !file.isEmpty())
                sponsorService.saveImmagine(sponsor, file);

            sponsorService.save(sponsor);

            model.addAttribute("sponsor", sponsorService.getAllSponsors());
            return "redirect:/";
        } else {
            model.addAttribute("messaggioErrore", "Questo sponsor esiste già");
            return "admin/formNewSponsor.html";
        }
    }

    @GetMapping("/admin/formUpdateSponsor/{id}")
    public String formEditSponsor(@PathVariable("id") Long id, Model model) {
        Sponsor sponsor = sponsorService.getSponsorById(id);

        if (sponsor != null) {
            model.addAttribute("sponsor", sponsor);

            return "admin/formUpdateSponsor.html";
        } else {
            model.addAttribute("messaggioErrore", "Sponsor non trovato.");
            model.addAttribute("sponsor", sponsorService.getAllSponsors());
            return "redirect:/";
        }
    }

    @PostMapping("/admin/formUpdateSponsor")
    public String updateSponsor(@ModelAttribute("team") Sponsor sponsor,
                             @RequestParam("file") MultipartFile file,
                             Model model) {
        // Controlla l'esistenza dello sponsor in base all'ID
        Sponsor sponsorEsistente = sponsorService.getSponsorById(sponsor.getId());

        if (sponsorEsistente != null) {
            // Aggiorna i dati
            sponsorEsistente.setNome(sponsor.getNome());
            sponsorEsistente.setDescrizione(sponsor.getDescrizione());

            // Aggiorna l'immagine, se caricata
            if (file != null && !file.isEmpty())
                sponsorService.saveImmagine(sponsorEsistente, file);

            // Salva le modifiche
            sponsorService.save(sponsorEsistente);

            model.addAttribute("sponsor", sponsorService.getAllSponsors());
            return "redirect:/";
        } else {
            model.addAttribute("messaggioErrore", "Team non trovato per l'aggiornamento.");
            return "admin/formNewSponsor.html";
        }
    }
}
