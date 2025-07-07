package it.uniroma3.siw.service;

import it.uniroma3.siw.model.GranPremio;
import it.uniroma3.siw.model.Pilota;
import it.uniroma3.siw.model.Sponsor;
import it.uniroma3.siw.model.Team;
import it.uniroma3.siw.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SponsorService {
    @Autowired
    private SponsorRepository sponsorRepository;
    @Autowired
    private ImmagineService immagineService;
    @Autowired
    private PilotaService pilotaService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private GranPremioService granPremioService;

    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    public List<Sponsor> findAllById(List<Long> sponsorIds) {
        return sponsorRepository.findAllById(sponsorIds);
    }

    @Transactional
    public void saveImmagine(Sponsor sponsor, MultipartFile file) {
        //Verifica se è già presente un'immagine ed eliminala
        String immaginePrecedente = sponsor.getImmagine();
        if (immaginePrecedente != null) {
            immagineService.deleteImage(immaginePrecedente);
        }

        // Salva l'immagine sul file system
        String fileName = immagineService.saveImage(file);

        // Collega l'immagine al libro
        sponsor.setImmagine(fileName);
    }

    public boolean existsByNome(String nome) {
        return sponsorRepository.existsByNomeIgnoreCase(nome);
    }

    public void save(Sponsor sponsor) {
        sponsorRepository.save(sponsor);
    }

    @Transactional
    public Sponsor getSponsorById(Long id) {
        return sponsorRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(Long id) {
        Sponsor sponsor = getSponsorById(id);

        if (sponsor == null) {
            throw new IllegalArgumentException("Sponsor non trovato con id: " + id);
        }

        // Rimuovi i riferimenti allo sponsor da tutte le entità correlate
        for (Pilota pilota : pilotaService.getAllPiloti()) {
            pilotaService.rimuoviSponsor(pilota, sponsor);
        }

        for (Team team : teamService.findAll()) {
            teamService.rimuoviSponsor(team, sponsor);
        }

        for (GranPremio granPremio : granPremioService.getAllGranPremi()) {
            granPremioService.rimuoviSponsor(granPremio, sponsor);
        }

        // Elimina lo sponsor
        sponsorRepository.deleteById(id);
    }

}
